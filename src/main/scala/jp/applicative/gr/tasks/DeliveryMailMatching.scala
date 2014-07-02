package jp.applicative.gr.tasks

import scalikejdbc._
import jp.applicative.gr.models._
import jp.applicative.gr.models.ex._
import org.joda.time.DateTime
import scalikejdbc.AutoSession
import org.joda.time.DateTimeZone
import org.slf4j.LoggerFactory

class DeliveryMailMatching {

  private val log = LoggerFactory.getLogger(this.getClass())

  val util = new MatchingUtil

  implicit val session = AutoSession

  private def matching_in(d: DeliveryMail, im: ImportMail) {
    d.tagText.map { (z: String) =>
      im.tagText.map { (m: String) =>
        val checked = util.check(z, m)
        val p = util.point(checked)

        if (p > 0) {
          val now = new DateTime(DateTimeZone.UTC)
          val dmm = DeliveryMailMatch.create(
            ownerId = None,
            deliveryMailId = d.id,
            importMailId = im.id,
            deliveryMailMatchType = "auto",
            matchingUserId = 0,
            memo = None,
            tagText = Some(checked.mkString(",")),
            paymentGap = d.payment.flatMap(x => im.payment.map(y => x - y)),
            ageGap = d.age.flatMap(x => im.age.map(y => x - y)),
            starred = None,
            createdAt = now,
            updatedAt = now,
            lockVersion = Some(0),
            createdUser = Some("MailMaching"),
            updatedUser = Some("MailMaching"),
            deletedAt = None,
            deleted = Some(0))
        }
      }

    }

    def matching() {
      val days: Int = SysConfigEx.deliveryMailTargetDays
      val last_id: Long = ImportMailEx.findLastId.getOrElse(0)
      for {
        d <- DeliveryMailEx.findBizOfferMails(days)
      } {
        val list = ImportMailEx.findBpMembersOrDays(d.autoMatchingLastId.getOrElse(0), last_id, days)
        list.foreach(matching_in(d, _))
        DeliveryMailEx.updateAutoMatchingLastId(d, last_id)
      }
      for {
        d <- DeliveryMailEx.findBpMemberMails(days)
      } {
        val list = ImportMailEx.findBizOffersOrDays(d.autoMatchingLastId.getOrElse(0), last_id, days)
        list.foreach(matching_in(d, _))
        DeliveryMailEx.updateAutoMatchingLastId(d, last_id)
      }
    }
  }

}