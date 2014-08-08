package jp.applicative.gr.models

import scalikejdbc._
import org.joda.time.{DateTime}

class DeliveryMail(
  val id: Long,
  val ownerId: Option[Long] = None,
  val deliveryMailType: String,
  val bpPicGroupId: Option[Long] = None,
  val bizOfferId: Option[Long] = None,
  val bpMemberId: Option[Long] = None,
  val mailStatusType: String,
  val subject: String,
  val content: String,
  val mailFromName: String,
  val mailFrom: String,
  val mailCc: Option[String] = None,
  val mailBcc: Option[String] = None,
  val plannedSettingAt: DateTime,
  val mailSendStatusType: String,
  val sendEndAt: Option[DateTime] = None,
  val tagText: Option[String] = None,
  val payment: Option[Double] = None,
  val age: Option[Int] = None,
  val autoMatchingLastId: Option[Long] = None,
  val importMailMatchId: Option[Long] = None,
  val matchingWayType: String,
  val createdAt: DateTime,
  val updatedAt: DateTime,
  val lockVersion: Option[Long] = None,
  val createdUser: Option[String] = None,
  val updatedUser: Option[String] = None,
  val deletedAt: Option[DateTime] = None,
  val deleted: Option[Int] = None) {

  def copy(
    id: Long = this.id,
    ownerId: Option[Long] = this.ownerId,
    deliveryMailType: String = this.deliveryMailType,
    bpPicGroupId: Option[Long] = this.bpPicGroupId,
    bizOfferId: Option[Long] = this.bizOfferId,
    bpMemberId: Option[Long] = this.bpMemberId,
    mailStatusType: String = this.mailStatusType,
    subject: String = this.subject,
    content: String = this.content,
    mailFromName: String = this.mailFromName,
    mailFrom: String = this.mailFrom,
    mailCc: Option[String] = this.mailCc,
    mailBcc: Option[String] = this.mailBcc,
    plannedSettingAt: DateTime = this.plannedSettingAt,
    mailSendStatusType: String = this.mailSendStatusType,
    sendEndAt: Option[DateTime] = this.sendEndAt,
    tagText: Option[String] = this.tagText,
    payment: Option[Double] = this.payment,
    age: Option[Int] = this.age,
    autoMatchingLastId: Option[Long] = this.autoMatchingLastId,
    importMailMatchId: Option[Long] = this.importMailMatchId,
    matchingWayType: String = this.matchingWayType,
    createdAt: DateTime = this.createdAt,
    updatedAt: DateTime = this.updatedAt,
    lockVersion: Option[Long] = this.lockVersion,
    createdUser: Option[String] = this.createdUser,
    updatedUser: Option[String] = this.updatedUser,
    deletedAt: Option[DateTime] = this.deletedAt,
    deleted: Option[Int] = this.deleted): DeliveryMail = {
    new DeliveryMail(
      id = id,
      ownerId = ownerId,
      deliveryMailType = deliveryMailType,
      bpPicGroupId = bpPicGroupId,
      bizOfferId = bizOfferId,
      bpMemberId = bpMemberId,
      mailStatusType = mailStatusType,
      subject = subject,
      content = content,
      mailFromName = mailFromName,
      mailFrom = mailFrom,
      mailCc = mailCc,
      mailBcc = mailBcc,
      plannedSettingAt = plannedSettingAt,
      mailSendStatusType = mailSendStatusType,
      sendEndAt = sendEndAt,
      tagText = tagText,
      payment = payment,
      age = age,
      autoMatchingLastId = autoMatchingLastId,
      importMailMatchId = importMailMatchId,
      matchingWayType = matchingWayType,
      createdAt = createdAt,
      updatedAt = updatedAt,
      lockVersion = lockVersion,
      createdUser = createdUser,
      updatedUser = updatedUser,
      deletedAt = deletedAt,
      deleted = deleted)
  }

  def save()(implicit session: DBSession = DeliveryMail.autoSession): DeliveryMail = DeliveryMail.save(this)(session)

  def destroy()(implicit session: DBSession = DeliveryMail.autoSession): Unit = DeliveryMail.destroy(this)(session)

}
      

object DeliveryMail extends SQLSyntaxSupport[DeliveryMail] {

  override val tableName = "delivery_mails"

  override val columns = Seq("id", "owner_id", "delivery_mail_type", "bp_pic_group_id", "biz_offer_id", "bp_member_id", "mail_status_type", "subject", "content", "mail_from_name", "mail_from", "mail_cc", "mail_bcc", "planned_setting_at", "mail_send_status_type", "send_end_at", "tag_text", "payment", "age", "auto_matching_last_id", "import_mail_match_id", "matching_way_type", "created_at", "updated_at", "lock_version", "created_user", "updated_user", "deleted_at", "deleted")

  def apply(dm: SyntaxProvider[DeliveryMail])(rs: WrappedResultSet): DeliveryMail = apply(dm.resultName)(rs)
  def apply(dm: ResultName[DeliveryMail])(rs: WrappedResultSet): DeliveryMail = new DeliveryMail(
    id = rs.get(dm.id),
    ownerId = rs.get(dm.ownerId),
    deliveryMailType = rs.get(dm.deliveryMailType),
    bpPicGroupId = rs.get(dm.bpPicGroupId),
    bizOfferId = rs.get(dm.bizOfferId),
    bpMemberId = rs.get(dm.bpMemberId),
    mailStatusType = rs.get(dm.mailStatusType),
    subject = rs.get(dm.subject),
    content = rs.get(dm.content),
    mailFromName = rs.get(dm.mailFromName),
    mailFrom = rs.get(dm.mailFrom),
    mailCc = rs.get(dm.mailCc),
    mailBcc = rs.get(dm.mailBcc),
    plannedSettingAt = rs.get(dm.plannedSettingAt),
    mailSendStatusType = rs.get(dm.mailSendStatusType),
    sendEndAt = rs.get(dm.sendEndAt),
    tagText = rs.get(dm.tagText),
    payment = rs.get(dm.payment),
    age = rs.get(dm.age),
    autoMatchingLastId = rs.get(dm.autoMatchingLastId),
    importMailMatchId = rs.get(dm.importMailMatchId),
    matchingWayType = rs.get(dm.matchingWayType),
    createdAt = rs.get(dm.createdAt),
    updatedAt = rs.get(dm.updatedAt),
    lockVersion = rs.get(dm.lockVersion),
    createdUser = rs.get(dm.createdUser),
    updatedUser = rs.get(dm.updatedUser),
    deletedAt = rs.get(dm.deletedAt),
    deleted = rs.get(dm.deleted)
  )
      
  val dm = DeliveryMail.syntax("dm")

  override val autoSession = AutoSession

  def find(id: Long)(implicit session: DBSession = autoSession): Option[DeliveryMail] = {
    withSQL {
      select.from(DeliveryMail as dm).where.eq(dm.id, id)
    }.map(DeliveryMail(dm.resultName)).single.apply()
  }
          
  def findAll()(implicit session: DBSession = autoSession): List[DeliveryMail] = {
    withSQL(select.from(DeliveryMail as dm)).map(DeliveryMail(dm.resultName)).list.apply()
  }
          
  def countAll()(implicit session: DBSession = autoSession): Long = {
    withSQL(select(sqls"count(1)").from(DeliveryMail as dm)).map(rs => rs.long(1)).single.apply().get
  }
          
  def findAllBy(where: SQLSyntax)(implicit session: DBSession = autoSession): List[DeliveryMail] = {
    withSQL { 
      select.from(DeliveryMail as dm).where.append(sqls"${where}")
    }.map(DeliveryMail(dm.resultName)).list.apply()
  }
      
  def countBy(where: SQLSyntax)(implicit session: DBSession = autoSession): Long = {
    withSQL { 
      select(sqls"count(1)").from(DeliveryMail as dm).where.append(sqls"${where}")
    }.map(_.long(1)).single.apply().get
  }
      
  def create(
    ownerId: Option[Long] = None,
    deliveryMailType: String,
    bpPicGroupId: Option[Long] = None,
    bizOfferId: Option[Long] = None,
    bpMemberId: Option[Long] = None,
    mailStatusType: String,
    subject: String,
    content: String,
    mailFromName: String,
    mailFrom: String,
    mailCc: Option[String] = None,
    mailBcc: Option[String] = None,
    plannedSettingAt: DateTime,
    mailSendStatusType: String,
    sendEndAt: Option[DateTime] = None,
    tagText: Option[String] = None,
    payment: Option[Double] = None,
    age: Option[Int] = None,
    autoMatchingLastId: Option[Long] = None,
    importMailMatchId: Option[Long] = None,
    matchingWayType: String,
    createdAt: DateTime,
    updatedAt: DateTime,
    lockVersion: Option[Long] = None,
    createdUser: Option[String] = None,
    updatedUser: Option[String] = None,
    deletedAt: Option[DateTime] = None,
    deleted: Option[Int] = None)(implicit session: DBSession = autoSession): DeliveryMail = {
    val generatedKey = withSQL {
      insert.into(DeliveryMail).columns(
        column.ownerId,
        column.deliveryMailType,
        column.bpPicGroupId,
        column.bizOfferId,
        column.bpMemberId,
        column.mailStatusType,
        column.subject,
        column.content,
        column.mailFromName,
        column.mailFrom,
        column.mailCc,
        column.mailBcc,
        column.plannedSettingAt,
        column.mailSendStatusType,
        column.sendEndAt,
        column.tagText,
        column.payment,
        column.age,
        column.autoMatchingLastId,
        column.importMailMatchId,
        column.matchingWayType,
        column.createdAt,
        column.updatedAt,
        column.lockVersion,
        column.createdUser,
        column.updatedUser,
        column.deletedAt,
        column.deleted
      ).values(
        ownerId,
        deliveryMailType,
        bpPicGroupId,
        bizOfferId,
        bpMemberId,
        mailStatusType,
        subject,
        content,
        mailFromName,
        mailFrom,
        mailCc,
        mailBcc,
        plannedSettingAt,
        mailSendStatusType,
        sendEndAt,
        tagText,
        payment,
        age,
        autoMatchingLastId,
        importMailMatchId,
        matchingWayType,
        createdAt,
        updatedAt,
        lockVersion,
        createdUser,
        updatedUser,
        deletedAt,
        deleted
      )
    }.updateAndReturnGeneratedKey.apply()

    new DeliveryMail(
      id = generatedKey, 
      ownerId = ownerId,
      deliveryMailType = deliveryMailType,
      bpPicGroupId = bpPicGroupId,
      bizOfferId = bizOfferId,
      bpMemberId = bpMemberId,
      mailStatusType = mailStatusType,
      subject = subject,
      content = content,
      mailFromName = mailFromName,
      mailFrom = mailFrom,
      mailCc = mailCc,
      mailBcc = mailBcc,
      plannedSettingAt = plannedSettingAt,
      mailSendStatusType = mailSendStatusType,
      sendEndAt = sendEndAt,
      tagText = tagText,
      payment = payment,
      age = age,
      autoMatchingLastId = autoMatchingLastId,
      importMailMatchId = importMailMatchId,
      matchingWayType = matchingWayType,
      createdAt = createdAt,
      updatedAt = updatedAt,
      lockVersion = lockVersion,
      createdUser = createdUser,
      updatedUser = updatedUser,
      deletedAt = deletedAt,
      deleted = deleted)
  }

  def save(entity: DeliveryMail)(implicit session: DBSession = autoSession): DeliveryMail = {
    withSQL {
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
        column.lockVersion -> entity.lockVersion,
        column.createdUser -> entity.createdUser,
        column.updatedUser -> entity.updatedUser,
        column.deletedAt -> entity.deletedAt,
        column.deleted -> entity.deleted
      ).where.eq(column.id, entity.id)
    }.update.apply()
    entity
  }
        
  def destroy(entity: DeliveryMail)(implicit session: DBSession = autoSession): Unit = {
    withSQL { delete.from(DeliveryMail).where.eq(column.id, entity.id) }.update.apply()
  }
        
}
