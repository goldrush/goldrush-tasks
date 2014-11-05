package jp.applicative.gr.tasks

import scalikejdbc._
import jp.applicative.gr.models._
import jp.applicative.gr.models.ex._
import org.joda.time.DateTime
import scalikejdbc.AutoSession
import org.joda.time.DateTimeZone
import org.slf4j.LoggerFactory

class ImportMailMatching(val owner_id: Option[Long], val session: DBSession) {

  private val log = LoggerFactory.getLogger(s"${this.getClass()}(owner: $owner_id)")

  val util = new MatchingUtil(owner_id)

  def matching: Long = {
    log.info("start")
    val sysConfig = new SysConfigEx(owner_id)
    val importMail = new ImportMailEx(owner_id)
    val result: Long = sysConfig.lastId(session) match {
      case None => importMail.findLastId(session).map { last_id =>
        sysConfig.createLastId(last_id)(session)
        log.info("It is the first execution. A part for the past is disregarded.")
        last_id
      }.getOrElse(0)
      case Some(last_id) =>
        log.debug(s"last_id = $last_id")
        importMail.findLastId(session).map { now_last_id =>
          val days = sysConfig.importMailTargetDays(session)

          log.info("find bizOffers")
          // 前回処理から追加された案件メールを取得し、複数件判定しフラグを立てつつ処理対象から外す
          val bizList = importMail.findBizOffers(last_id, now_last_id)(session).map(pluralAnalyze).filter(_.pluralFlg.getOrElse(0) == 0)
          if (bizList.nonEmpty) {
            log.info("matching bizOffers to bpMembers")
            val bpmTargetList = importMail.findBpMemberTargets(now_last_id, days)(session) // 非対称 biz -> bpは最新から、bp -> bizは前回最終からマッチング。最新×最新がダブるのを避ける
            matching_in(bizList, bpmTargetList)
          }

          log.info("find bpMembers")
          // 前回処理から追加された人材メールを取得し、複数件判定しフラグを立てつつ処理対象から外す
          val bpmList = importMail.findBpMembers(last_id, now_last_id)(session).map(pluralAnalyze).filter(_.pluralFlg.getOrElse(0) == 0)
          if (bpmList.nonEmpty) {
            log.info("matching bpMembers to bizOffers")
            val bizTargetList = importMail.findBizOfferTargets(last_id, days)(session)
            matching_in(bizTargetList, bpmList)
          }

          sysConfig.createOrUpdateLastId(now_last_id)(session)
          now_last_id
        }.getOrElse(last_id)
    }
    log.info(s"end now_last_id = $result")
    result
  }

  /**
   * 件名タグが存在する && 有効なタグ
   *   -> 人材側のタグにそのタグが存在する  = 2
   *   -> 人材側のタグにそのタグが存在しない  = 0
   * 有効な件名タグが存在しない = 1
   */
  def subjectTagMatchinFlg(subjectTagText: Option[String], m: String): Some[Int] = Some(subjectTagText match {
    case Some(x) =>
      if (util.point(util.normalizeTagList(x)) > 0) {
        if (util.check(x, m).isEmpty) 0 else 2
      } else 1
    case None => 1
  })
  
  private def matching_in(bizList: List[ImportMail], bpmList: List[ImportMail]) {
    for {
      biz <- bizList
      bpm <- bpmList
      if biz.id != bpm.id // 別メールなら
      if !(biz.businessPartnerId.nonEmpty && bpm.businessPartnerId.nonEmpty && biz.businessPartnerId == bpm.businessPartnerId) // 別会社なら
    } yield {
      biz.tagText.map { z: String =>
        bpm.tagText.map { m: String =>
          val checked = util.check(z, m)
          val p = util.point(checked)

          if (p > 0) {
            val now = new DateTime(DateTimeZone.UTC)
            ImportMailMatch.create(
              ownerId = owner_id,
              bizOfferMailId = biz.id,
              bpMemberMailId = bpm.id,
              mailMatchScore = Some(p),
              tagText = Some(checked.mkString(",")),
              subjectTagMatchFlg = subjectTagMatchinFlg(biz.subjectTagText, m),
              immStatusType = "open",
              paymentGap = biz.payment.flatMap(x => bpm.payment.map(y => x - y)),
              ageGap = biz.age.flatMap(x => bpm.age.map(y => x - y)),
              starred = Some(0),
              receivedAt = (if (biz.receivedAt.compareTo(bpm.receivedAt) < 0) { biz.receivedAt } else { bpm.receivedAt }),
              createdAt = now,
              updatedAt = now,
              lockVersion = Some(0),
              createdUser = Some("MailMaching"),
              updatedUser = Some("MailMaching"),
              deletedAt = None,
              deleted = Some(0))(session)
          }
        }
      }
    }
  }

  /**
   * 複数件フラグを立ててDB保存しつつ、フラグ処理後のImportMailを返す(破壊的)
   */
  def pluralAnalyze(im: ImportMail): ImportMail = {
    if (util.isPlural(im.mailBody)) {
      //log.debug("isPlural: " + im.id)
      im.copy(updatedAt = new DateTime(DateTimeZone.UTC),
        lockVersion = im.lockVersion.map(_ + 1),
        updatedUser = Some("PluralAnalyze")).save()(session)
    } else im
  }

}