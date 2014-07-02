package jp.applicative.gr.models.ex

import scalikejdbc._
import org.joda.time.{ DateTime, DateTimeZone }
import jp.applicative.gr.models._

object DeliveryMailEx {

  val dm = DeliveryMail.syntax("dm")
  val g = BpPicGroup.syntax("g")

  def findTargetMails(days: Int, bpPicGroupType: String)(implicit session: DBSession) = {
    withSQL {
      select.from(DeliveryMail as dm).join(BpPicGroup as g).on(g.id, dm.bpPicGroupId)
        .where.gt(dm.createdAt, (new DateTime(DateTimeZone.UTC)).minusDays(days))
        .and.eq(g.bpPicGroupType, bpPicGroupType)
        .and.eq(g.deleted, 0)
        .and.eq(dm.deleted, 0)
    }.map(DeliveryMail(dm.resultName)).list.apply()
  }

  def findBizOfferMails(days: Int)(implicit session: DBSession) = findTargetMails(days, "biz_offer")

  def findBpMemberMails(days: Int)(implicit session: DBSession) = findTargetMails(days, "bp_member")

  def updateAutoMatchingLastId(d: DeliveryMail, autoMatchingLastId: Long) {
    DeliveryMail.save(
      new DeliveryMail(
        id = d.id,
        ownerId = d.ownerId,
        deliveryMailType = d.deliveryMailType,
        bpPicGroupId = d.bpPicGroupId,
        bizOfferId = d.bizOfferId,
        bpMemberId = d.bpMemberId,
        mailStatusType = d.mailStatusType,
        subject = d.subject,
        content = d.content,
        mailFromName = d.mailFromName,
        mailFrom = d.mailFrom,
        mailCc = d.mailCc,
        mailBcc = d.mailBcc,
        plannedSettingAt = d.plannedSettingAt,
        mailSendStatusType = d.mailSendStatusType,
        sendEndAt = d.sendEndAt,
        tagText = d.tagText,
        payment = d.payment,
        age = d.age,
        autoMatchingLastId = Some(autoMatchingLastId),
        createdAt = d.createdAt,
        updatedAt = new DateTime(DateTimeZone.UTC),
        lockVersion = d.lockVersion,
        createdUser = d.createdUser,
        updatedUser = Some("MailMatching"),
        deletedAt = d.deletedAt,
        deleted = d.deleted))
  }

}