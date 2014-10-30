package jp.applicative.gr.models.ex

import scalikejdbc._
import org.joda.time.{ DateTime, DateTimeZone }
import jp.applicative.gr.models._
import scala.annotation.tailrec

class RetryCountOverError extends RuntimeException

object DeliveryMailEx extends SQLSyntaxSupport[DeliveryMail] {

  override val tableName = "delivery_mails"

  override val columns = Seq("id", "owner_id", "delivery_mail_type", "bp_pic_group_id", "biz_offer_id", "bp_member_id", "mail_status_type", "subject", "content", "mail_from_name", "mail_from", "mail_cc", "mail_bcc", "planned_setting_at", "mail_send_status_type", "send_end_at", "tag_text", "payment", "age", "auto_matching_last_id", "import_mail_match_id", "matching_way_type", "created_at", "updated_at", "lock_version", "created_user", "updated_user", "deleted_at", "deleted")

  val dm = DeliveryMail.syntax("dm")
  val g = BpPicGroup.syntax("g")

  def findTargetMails(owner_id: Long)(days: Int, matchingWayType: String)(implicit session: DBSession) = {
    withSQL {
      select.from(DeliveryMail as dm).join(BpPicGroup as g).on(g.id, dm.bpPicGroupId)
        .where.eq(dm.ownerId, owner_id)
        .and.gt(dm.createdAt, (new DateTime(DateTimeZone.UTC)).minusDays(days))
        .and.eq(g.matchingWayType, matchingWayType)
        .and.ne(dm.mailStatusType, "editing")
        .and.eq(g.deleted, 0)
        .and.eq(dm.deleted, 0)
    }.map(DeliveryMail(dm.resultName)).list.apply()
  }

  def findBizOfferMails(owner_id: Long)(days: Int)(implicit session: DBSession) = findTargetMails(owner_id)(days, "biz_offer")

  def findBpMemberMails(owner_id: Long)(days: Int)(implicit session: DBSession) = findTargetMails(owner_id)(days, "bp_member")

  def updateAutoMatchingLastId(d: DeliveryMail, autoMatchingLastId: Long)(implicit session: DBSession) {
    DeliveryMailEx.saveWithLockVersion(
      d.copy(
        autoMatchingLastId = Some(autoMatchingLastId),
        updatedAt = new DateTime(DateTimeZone.UTC),
        updatedUser = Some("MailMatching")))
  }

  @tailrec
  def saveWithLockVersion(entity: DeliveryMail, retry: Int = 60)(implicit session: DBSession): DeliveryMail = {
    val rows: Int = withSQL {
      update(DeliveryMail).set(
        column.id -> entity.id,
        column.ownerId -> entity.ownerId,
        column.deliveryMailType -> entity.deliveryMailType,
        column.bpPicGroupId -> entity.bpPicGroupId,
        column.bizOfferId -> entity.bizOfferId,
        column.bpMemberId -> entity.bpMemberId,
        column.mailStatusType -> entity.mailStatusType,
        column.subject -> entity.subject,
        column.content -> entity.content,
        column.mailFromName -> entity.mailFromName,
        column.mailFrom -> entity.mailFrom,
        column.mailCc -> entity.mailCc,
        column.mailBcc -> entity.mailBcc,
        column.plannedSettingAt -> entity.plannedSettingAt,
        column.mailSendStatusType -> entity.mailSendStatusType,
        column.sendEndAt -> entity.sendEndAt,
        column.tagText -> entity.tagText,
        column.payment -> entity.payment,
        column.age -> entity.age,
        column.autoMatchingLastId -> entity.autoMatchingLastId,
        column.importMailMatchId -> entity.importMailMatchId,
        column.matchingWayType -> entity.matchingWayType,
        column.createdAt -> entity.createdAt,
        column.updatedAt -> entity.updatedAt,
        column.lockVersion -> entity.lockVersion.map(_ + 1),
        column.createdUser -> entity.createdUser,
        column.updatedUser -> entity.updatedUser,
        column.deletedAt -> entity.deletedAt,
        column.deleted -> entity.deleted).where.eq(column.id, entity.id).and.eq(column.lockVersion, entity.lockVersion)
    }.update.apply()

    if (rows > 0) {
      entity
    } else if (0 >= retry) {
      throw new RetryCountOverError()
    } else {
      Thread.sleep(1000)
      saveWithLockVersion(entity, retry - 1)
    }
  }
  
  def deleteDeliveryMailMatches(days: Int)(implicit session: DBSession) {
    SQL("create table new_delivery_mail_matches like delivery_mail_matches").execute().apply()(session)
    SQL("insert into new_delivery_mail_matches select * from delivery_mail_matches where created_at > date_add(current_timestamp(), interval - 14 day)").execute().apply()(session)
    SQL("rename table delivery_mail_matches to old_delivery_mail_matches, new_delivery_mail_matches to delivery_mail_matches").execute().apply()(session)
    SQL("drop table old_delivery_mail_matches").execute().apply()(session)
  }
}