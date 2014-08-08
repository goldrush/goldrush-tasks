package jp.applicative.gr.models

import scalikejdbc._
import org.joda.time.{DateTime}

class ImportMail(
  val id: Long,
  val ownerId: Option[Long] = None,
  val businessPartnerId: Option[Long] = None,
  val bpPicId: Option[Long] = None,
  val inReplyTo: Option[String] = None,
  val deliveryMailId: Option[Long] = None,
  val receivedAt: DateTime,
  val mailSubject: String,
  val mailBody: String,
  val mailFrom: String,
  val mailSenderName: String,
  val mailTo: Option[String] = None,
  val mailCc: Option[String] = None,
  val mailBcc: Option[String] = None,
  val messageId: Option[String] = None,
  val bizOfferFlg: Option[Int] = None,
  val bpMemberFlg: Option[Int] = None,
  val registed: Option[Int] = None,
  val unwanted: Option[Int] = None,
  val properFlg: Option[Int] = None,
  val outflowMailFlg: Option[Int] = None,
  val starred: Option[Int] = None,
  val tagText: Option[String] = None,
  val subjectTagText: Option[String] = None,
  val payment: Option[Double] = None,
  val age: Option[Int] = None,
  val paymentText: Option[String] = None,
  val ageText: Option[String] = None,
  val nearestStation: Option[String] = None,
  val pluralFlg: Option[Int] = None,
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
    businessPartnerId: Option[Long] = this.businessPartnerId,
    bpPicId: Option[Long] = this.bpPicId,
    inReplyTo: Option[String] = this.inReplyTo,
    deliveryMailId: Option[Long] = this.deliveryMailId,
    receivedAt: DateTime = this.receivedAt,
    mailSubject: String = this.mailSubject,
    mailBody: String = this.mailBody,
    mailFrom: String = this.mailFrom,
    mailSenderName: String = this.mailSenderName,
    mailTo: Option[String] = this.mailTo,
    mailCc: Option[String] = this.mailCc,
    mailBcc: Option[String] = this.mailBcc,
    messageId: Option[String] = this.messageId,
    bizOfferFlg: Option[Int] = this.bizOfferFlg,
    bpMemberFlg: Option[Int] = this.bpMemberFlg,
    registed: Option[Int] = this.registed,
    unwanted: Option[Int] = this.unwanted,
    properFlg: Option[Int] = this.properFlg,
    outflowMailFlg: Option[Int] = this.outflowMailFlg,
    starred: Option[Int] = this.starred,
    tagText: Option[String] = this.tagText,
    subjectTagText: Option[String] = this.subjectTagText,
    payment: Option[Double] = this.payment,
    age: Option[Int] = this.age,
    paymentText: Option[String] = this.paymentText,
    ageText: Option[String] = this.ageText,
    nearestStation: Option[String] = this.nearestStation,
    pluralFlg: Option[Int] = this.pluralFlg,
    importMailMatchId: Option[Long] = this.importMailMatchId,
    matchingWayType: String = this.matchingWayType,
    createdAt: DateTime = this.createdAt,
    updatedAt: DateTime = this.updatedAt,
    lockVersion: Option[Long] = this.lockVersion,
    createdUser: Option[String] = this.createdUser,
    updatedUser: Option[String] = this.updatedUser,
    deletedAt: Option[DateTime] = this.deletedAt,
    deleted: Option[Int] = this.deleted): ImportMail = {
    new ImportMail(
      id = id,
      ownerId = ownerId,
      businessPartnerId = businessPartnerId,
      bpPicId = bpPicId,
      inReplyTo = inReplyTo,
      deliveryMailId = deliveryMailId,
      receivedAt = receivedAt,
      mailSubject = mailSubject,
      mailBody = mailBody,
      mailFrom = mailFrom,
      mailSenderName = mailSenderName,
      mailTo = mailTo,
      mailCc = mailCc,
      mailBcc = mailBcc,
      messageId = messageId,
      bizOfferFlg = bizOfferFlg,
      bpMemberFlg = bpMemberFlg,
      registed = registed,
      unwanted = unwanted,
      properFlg = properFlg,
      outflowMailFlg = outflowMailFlg,
      starred = starred,
      tagText = tagText,
      subjectTagText = subjectTagText,
      payment = payment,
      age = age,
      paymentText = paymentText,
      ageText = ageText,
      nearestStation = nearestStation,
      pluralFlg = pluralFlg,
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

  def save()(implicit session: DBSession = ImportMail.autoSession): ImportMail = ImportMail.save(this)(session)

  def destroy()(implicit session: DBSession = ImportMail.autoSession): Unit = ImportMail.destroy(this)(session)

}
      

object ImportMail extends SQLSyntaxSupport[ImportMail] {

  override val tableName = "import_mails"

  override val columns = Seq("id", "owner_id", "business_partner_id", "bp_pic_id", "in_reply_to", "delivery_mail_id", "received_at", "mail_subject", "mail_body", "mail_from", "mail_sender_name", "mail_to", "mail_cc", "mail_bcc", "message_id", "biz_offer_flg", "bp_member_flg", "registed", "unwanted", "proper_flg", "outflow_mail_flg", "starred", "tag_text", "subject_tag_text", "payment", "age", "payment_text", "age_text", "nearest_station", "plural_flg", "import_mail_match_id", "matching_way_type", "created_at", "updated_at", "lock_version", "created_user", "updated_user", "deleted_at", "deleted")

  def apply(im: SyntaxProvider[ImportMail])(rs: WrappedResultSet): ImportMail = apply(im.resultName)(rs)
  def apply(im: ResultName[ImportMail])(rs: WrappedResultSet): ImportMail = new ImportMail(
    id = rs.get(im.id),
    ownerId = rs.get(im.ownerId),
    businessPartnerId = rs.get(im.businessPartnerId),
    bpPicId = rs.get(im.bpPicId),
    inReplyTo = rs.get(im.inReplyTo),
    deliveryMailId = rs.get(im.deliveryMailId),
    receivedAt = rs.get(im.receivedAt),
    mailSubject = rs.get(im.mailSubject),
    mailBody = rs.get(im.mailBody),
    mailFrom = rs.get(im.mailFrom),
    mailSenderName = rs.get(im.mailSenderName),
    mailTo = rs.get(im.mailTo),
    mailCc = rs.get(im.mailCc),
    mailBcc = rs.get(im.mailBcc),
    messageId = rs.get(im.messageId),
    bizOfferFlg = rs.get(im.bizOfferFlg),
    bpMemberFlg = rs.get(im.bpMemberFlg),
    registed = rs.get(im.registed),
    unwanted = rs.get(im.unwanted),
    properFlg = rs.get(im.properFlg),
    outflowMailFlg = rs.get(im.outflowMailFlg),
    starred = rs.get(im.starred),
    tagText = rs.get(im.tagText),
    subjectTagText = rs.get(im.subjectTagText),
    payment = rs.get(im.payment),
    age = rs.get(im.age),
    paymentText = rs.get(im.paymentText),
    ageText = rs.get(im.ageText),
    nearestStation = rs.get(im.nearestStation),
    pluralFlg = rs.get(im.pluralFlg),
    importMailMatchId = rs.get(im.importMailMatchId),
    matchingWayType = rs.get(im.matchingWayType),
    createdAt = rs.get(im.createdAt),
    updatedAt = rs.get(im.updatedAt),
    lockVersion = rs.get(im.lockVersion),
    createdUser = rs.get(im.createdUser),
    updatedUser = rs.get(im.updatedUser),
    deletedAt = rs.get(im.deletedAt),
    deleted = rs.get(im.deleted)
  )
      
  val im = ImportMail.syntax("im")

  override val autoSession = AutoSession

  def find(id: Long)(implicit session: DBSession = autoSession): Option[ImportMail] = {
    withSQL {
      select.from(ImportMail as im).where.eq(im.id, id)
    }.map(ImportMail(im.resultName)).single.apply()
  }
          
  def findAll()(implicit session: DBSession = autoSession): List[ImportMail] = {
    withSQL(select.from(ImportMail as im)).map(ImportMail(im.resultName)).list.apply()
  }
          
  def countAll()(implicit session: DBSession = autoSession): Long = {
    withSQL(select(sqls"count(1)").from(ImportMail as im)).map(rs => rs.long(1)).single.apply().get
  }
          
  def findAllBy(where: SQLSyntax)(implicit session: DBSession = autoSession): List[ImportMail] = {
    withSQL { 
      select.from(ImportMail as im).where.append(sqls"${where}")
    }.map(ImportMail(im.resultName)).list.apply()
  }
      
  def countBy(where: SQLSyntax)(implicit session: DBSession = autoSession): Long = {
    withSQL { 
      select(sqls"count(1)").from(ImportMail as im).where.append(sqls"${where}")
    }.map(_.long(1)).single.apply().get
  }
      
  def create(
    ownerId: Option[Long] = None,
    businessPartnerId: Option[Long] = None,
    bpPicId: Option[Long] = None,
    inReplyTo: Option[String] = None,
    deliveryMailId: Option[Long] = None,
    receivedAt: DateTime,
    mailSubject: String,
    mailBody: String,
    mailFrom: String,
    mailSenderName: String,
    mailTo: Option[String] = None,
    mailCc: Option[String] = None,
    mailBcc: Option[String] = None,
    messageId: Option[String] = None,
    bizOfferFlg: Option[Int] = None,
    bpMemberFlg: Option[Int] = None,
    registed: Option[Int] = None,
    unwanted: Option[Int] = None,
    properFlg: Option[Int] = None,
    outflowMailFlg: Option[Int] = None,
    starred: Option[Int] = None,
    tagText: Option[String] = None,
    subjectTagText: Option[String] = None,
    payment: Option[Double] = None,
    age: Option[Int] = None,
    paymentText: Option[String] = None,
    ageText: Option[String] = None,
    nearestStation: Option[String] = None,
    pluralFlg: Option[Int] = None,
    importMailMatchId: Option[Long] = None,
    matchingWayType: String,
    createdAt: DateTime,
    updatedAt: DateTime,
    lockVersion: Option[Long] = None,
    createdUser: Option[String] = None,
    updatedUser: Option[String] = None,
    deletedAt: Option[DateTime] = None,
    deleted: Option[Int] = None)(implicit session: DBSession = autoSession): ImportMail = {
    val generatedKey = withSQL {
      insert.into(ImportMail).columns(
        column.ownerId,
        column.businessPartnerId,
        column.bpPicId,
        column.inReplyTo,
        column.deliveryMailId,
        column.receivedAt,
        column.mailSubject,
        column.mailBody,
        column.mailFrom,
        column.mailSenderName,
        column.mailTo,
        column.mailCc,
        column.mailBcc,
        column.messageId,
        column.bizOfferFlg,
        column.bpMemberFlg,
        column.registed,
        column.unwanted,
        column.properFlg,
        column.outflowMailFlg,
        column.starred,
        column.tagText,
        column.subjectTagText,
        column.payment,
        column.age,
        column.paymentText,
        column.ageText,
        column.nearestStation,
        column.pluralFlg,
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
        businessPartnerId,
        bpPicId,
        inReplyTo,
        deliveryMailId,
        receivedAt,
        mailSubject,
        mailBody,
        mailFrom,
        mailSenderName,
        mailTo,
        mailCc,
        mailBcc,
        messageId,
        bizOfferFlg,
        bpMemberFlg,
        registed,
        unwanted,
        properFlg,
        outflowMailFlg,
        starred,
        tagText,
        subjectTagText,
        payment,
        age,
        paymentText,
        ageText,
        nearestStation,
        pluralFlg,
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

    new ImportMail(
      id = generatedKey, 
      ownerId = ownerId,
      businessPartnerId = businessPartnerId,
      bpPicId = bpPicId,
      inReplyTo = inReplyTo,
      deliveryMailId = deliveryMailId,
      receivedAt = receivedAt,
      mailSubject = mailSubject,
      mailBody = mailBody,
      mailFrom = mailFrom,
      mailSenderName = mailSenderName,
      mailTo = mailTo,
      mailCc = mailCc,
      mailBcc = mailBcc,
      messageId = messageId,
      bizOfferFlg = bizOfferFlg,
      bpMemberFlg = bpMemberFlg,
      registed = registed,
      unwanted = unwanted,
      properFlg = properFlg,
      outflowMailFlg = outflowMailFlg,
      starred = starred,
      tagText = tagText,
      subjectTagText = subjectTagText,
      payment = payment,
      age = age,
      paymentText = paymentText,
      ageText = ageText,
      nearestStation = nearestStation,
      pluralFlg = pluralFlg,
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

  def save(entity: ImportMail)(implicit session: DBSession = autoSession): ImportMail = {
    withSQL {
      update(ImportMail).set(
        column.id -> entity.id,
        column.ownerId -> entity.ownerId,
        column.businessPartnerId -> entity.businessPartnerId,
        column.bpPicId -> entity.bpPicId,
        column.inReplyTo -> entity.inReplyTo,
        column.deliveryMailId -> entity.deliveryMailId,
        column.receivedAt -> entity.receivedAt,
        column.mailSubject -> entity.mailSubject,
        column.mailBody -> entity.mailBody,
        column.mailFrom -> entity.mailFrom,
        column.mailSenderName -> entity.mailSenderName,
        column.mailTo -> entity.mailTo,
        column.mailCc -> entity.mailCc,
        column.mailBcc -> entity.mailBcc,
        column.messageId -> entity.messageId,
        column.bizOfferFlg -> entity.bizOfferFlg,
        column.bpMemberFlg -> entity.bpMemberFlg,
        column.registed -> entity.registed,
        column.unwanted -> entity.unwanted,
        column.properFlg -> entity.properFlg,
        column.outflowMailFlg -> entity.outflowMailFlg,
        column.starred -> entity.starred,
        column.tagText -> entity.tagText,
        column.subjectTagText -> entity.subjectTagText,
        column.payment -> entity.payment,
        column.age -> entity.age,
        column.paymentText -> entity.paymentText,
        column.ageText -> entity.ageText,
        column.nearestStation -> entity.nearestStation,
        column.pluralFlg -> entity.pluralFlg,
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
        
  def destroy(entity: ImportMail)(implicit session: DBSession = autoSession): Unit = {
    withSQL { delete.from(ImportMail).where.eq(column.id, entity.id) }.update.apply()
  }
        
}
