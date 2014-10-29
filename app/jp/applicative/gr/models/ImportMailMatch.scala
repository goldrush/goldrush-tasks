package jp.applicative.gr.models

import scalikejdbc._
import org.joda.time.{DateTime}

case class ImportMailMatch(
  id: Long, 
  ownerId: Option[Long] = None, 
  bizOfferMailId: Long, 
  bpMemberMailId: Long, 
  mailMatchScore: Option[Long] = None, 
  tagText: Option[String] = None, 
  subjectTagMatchFlg: Option[Int] = None, 
  immStatusType: String, 
  paymentGap: Option[Double] = None, 
  ageGap: Option[Int] = None, 
  starred: Option[Int] = None, 
  receivedAt: DateTime, 
  createdAt: DateTime, 
  updatedAt: DateTime, 
  lockVersion: Option[Long] = None, 
  createdUser: Option[String] = None, 
  updatedUser: Option[String] = None, 
  deletedAt: Option[DateTime] = None, 
  deleted: Option[Int] = None) {

  def save()(implicit session: DBSession): ImportMailMatch = ImportMailMatch.save(this)(session)

  def destroy()(implicit session: DBSession): Unit = ImportMailMatch.destroy(this)(session)

}
      

object ImportMailMatch extends SQLSyntaxSupport[ImportMailMatch] {

  override val tableName = "import_mail_matches"

  override val columns = Seq("id", "owner_id", "biz_offer_mail_id", "bp_member_mail_id", "mail_match_score", "tag_text", "subject_tag_match_flg", "imm_status_type", "payment_gap", "age_gap", "starred", "received_at", "created_at", "updated_at", "lock_version", "created_user", "updated_user", "deleted_at", "deleted")

  def apply(imm: SyntaxProvider[ImportMailMatch])(rs: WrappedResultSet): ImportMailMatch = apply(imm.resultName)(rs)
  def apply(imm: ResultName[ImportMailMatch])(rs: WrappedResultSet): ImportMailMatch = new ImportMailMatch(
    id = rs.get(imm.id),
    ownerId = rs.get(imm.ownerId),
    bizOfferMailId = rs.get(imm.bizOfferMailId),
    bpMemberMailId = rs.get(imm.bpMemberMailId),
    mailMatchScore = rs.get(imm.mailMatchScore),
    tagText = rs.get(imm.tagText),
    subjectTagMatchFlg = rs.get(imm.subjectTagMatchFlg),
    immStatusType = rs.get(imm.immStatusType),
    paymentGap = rs.get(imm.paymentGap),
    ageGap = rs.get(imm.ageGap),
    starred = rs.get(imm.starred),
    receivedAt = rs.get(imm.receivedAt),
    createdAt = rs.get(imm.createdAt),
    updatedAt = rs.get(imm.updatedAt),
    lockVersion = rs.get(imm.lockVersion),
    createdUser = rs.get(imm.createdUser),
    updatedUser = rs.get(imm.updatedUser),
    deletedAt = rs.get(imm.deletedAt),
    deleted = rs.get(imm.deleted)
  )
      
  val imm = ImportMailMatch.syntax("imm")

  override val autoSession = AutoSession

  def find(id: Long)(implicit session: DBSession): Option[ImportMailMatch] = {
    withSQL {
      select.from(ImportMailMatch as imm).where.eq(imm.id, id)
    }.map(ImportMailMatch(imm.resultName)).single.apply()
  }
          
  def findAll()(implicit session: DBSession): List[ImportMailMatch] = {
    withSQL(select.from(ImportMailMatch as imm)).map(ImportMailMatch(imm.resultName)).list.apply()
  }
          
  def countAll()(implicit session: DBSession): Long = {
    withSQL(select(sqls"count(1)").from(ImportMailMatch as imm)).map(rs => rs.long(1)).single.apply().get
  }
          
  def findAllBy(where: SQLSyntax)(implicit session: DBSession): List[ImportMailMatch] = {
    withSQL { 
      select.from(ImportMailMatch as imm).where.append(sqls"${where}")
    }.map(ImportMailMatch(imm.resultName)).list.apply()
  }
      
  def countBy(where: SQLSyntax)(implicit session: DBSession): Long = {
    withSQL { 
      select(sqls"count(1)").from(ImportMailMatch as imm).where.append(sqls"${where}")
    }.map(_.long(1)).single.apply().get
  }
      
  def create(
    ownerId: Option[Long] = None,
    bizOfferMailId: Long,
    bpMemberMailId: Long,
    mailMatchScore: Option[Long] = None,
    tagText: Option[String] = None,
    subjectTagMatchFlg: Option[Int] = None,
    immStatusType: String,
    paymentGap: Option[Double] = None,
    ageGap: Option[Int] = None,
    starred: Option[Int] = None,
    receivedAt: DateTime,
    createdAt: DateTime,
    updatedAt: DateTime,
    lockVersion: Option[Long] = None,
    createdUser: Option[String] = None,
    updatedUser: Option[String] = None,
    deletedAt: Option[DateTime] = None,
    deleted: Option[Int] = None)(implicit session: DBSession): ImportMailMatch = {
    val generatedKey = withSQL {
      insert.into(ImportMailMatch).columns(
        column.ownerId,
        column.bizOfferMailId,
        column.bpMemberMailId,
        column.mailMatchScore,
        column.tagText,
        column.subjectTagMatchFlg,
        column.immStatusType,
        column.paymentGap,
        column.ageGap,
        column.starred,
        column.receivedAt,
        column.createdAt,
        column.updatedAt,
        column.lockVersion,
        column.createdUser,
        column.updatedUser,
        column.deletedAt,
        column.deleted
      ).values(
        ownerId,
        bizOfferMailId,
        bpMemberMailId,
        mailMatchScore,
        tagText,
        subjectTagMatchFlg,
        immStatusType,
        paymentGap,
        ageGap,
        starred,
        receivedAt,
        createdAt,
        updatedAt,
        lockVersion,
        createdUser,
        updatedUser,
        deletedAt,
        deleted
      )
    }.updateAndReturnGeneratedKey.apply()

    ImportMailMatch(
      id = generatedKey, 
      ownerId = ownerId,
      bizOfferMailId = bizOfferMailId,
      bpMemberMailId = bpMemberMailId,
      mailMatchScore = mailMatchScore,
      tagText = tagText,
      subjectTagMatchFlg = subjectTagMatchFlg,
      immStatusType = immStatusType,
      paymentGap = paymentGap,
      ageGap = ageGap,
      starred = starred,
      receivedAt = receivedAt,
      createdAt = createdAt,
      updatedAt = updatedAt,
      lockVersion = lockVersion,
      createdUser = createdUser,
      updatedUser = updatedUser,
      deletedAt = deletedAt,
      deleted = deleted)
  }

  def save(entity: ImportMailMatch)(implicit session: DBSession): ImportMailMatch = {
    withSQL {
      update(ImportMailMatch).set(
        column.id -> entity.id,
        column.ownerId -> entity.ownerId,
        column.bizOfferMailId -> entity.bizOfferMailId,
        column.bpMemberMailId -> entity.bpMemberMailId,
        column.mailMatchScore -> entity.mailMatchScore,
        column.tagText -> entity.tagText,
        column.subjectTagMatchFlg -> entity.subjectTagMatchFlg,
        column.immStatusType -> entity.immStatusType,
        column.paymentGap -> entity.paymentGap,
        column.ageGap -> entity.ageGap,
        column.starred -> entity.starred,
        column.receivedAt -> entity.receivedAt,
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
        
  def destroy(entity: ImportMailMatch)(implicit session: DBSession): Unit = {
    withSQL { delete.from(ImportMailMatch).where.eq(column.id, entity.id) }.update.apply()
  }
        
}
