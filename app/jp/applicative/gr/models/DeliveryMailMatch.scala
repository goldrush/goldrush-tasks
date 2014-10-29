package jp.applicative.gr.models

import scalikejdbc._
import org.joda.time.{DateTime}

case class DeliveryMailMatch(
  id: Long, 
  ownerId: Option[Long] = None, 
  deliveryMailId: Long, 
  importMailId: Long, 
  deliveryMailMatchType: String, 
  matchingUserId: Long, 
  memo: Option[String] = None, 
  tagText: Option[String] = None, 
  paymentGap: Option[Double] = None, 
  ageGap: Option[Int] = None, 
  starred: Option[Int] = None, 
  createdAt: DateTime, 
  updatedAt: DateTime, 
  lockVersion: Option[Long] = None, 
  createdUser: Option[String] = None, 
  updatedUser: Option[String] = None, 
  deletedAt: Option[DateTime] = None, 
  deleted: Option[Int] = None) {

  def save()(implicit session: DBSession): DeliveryMailMatch = DeliveryMailMatch.save(this)(session)

  def destroy()(implicit session: DBSession): Unit = DeliveryMailMatch.destroy(this)(session)

}
      

object DeliveryMailMatch extends SQLSyntaxSupport[DeliveryMailMatch] {

  override val tableName = "delivery_mail_matches"

  override val columns = Seq("id", "owner_id", "delivery_mail_id", "import_mail_id", "delivery_mail_match_type", "matching_user_id", "memo", "tag_text", "payment_gap", "age_gap", "starred", "created_at", "updated_at", "lock_version", "created_user", "updated_user", "deleted_at", "deleted")

  def apply(dmm: SyntaxProvider[DeliveryMailMatch])(rs: WrappedResultSet): DeliveryMailMatch = apply(dmm.resultName)(rs)
  def apply(dmm: ResultName[DeliveryMailMatch])(rs: WrappedResultSet): DeliveryMailMatch = new DeliveryMailMatch(
    id = rs.get(dmm.id),
    ownerId = rs.get(dmm.ownerId),
    deliveryMailId = rs.get(dmm.deliveryMailId),
    importMailId = rs.get(dmm.importMailId),
    deliveryMailMatchType = rs.get(dmm.deliveryMailMatchType),
    matchingUserId = rs.get(dmm.matchingUserId),
    memo = rs.get(dmm.memo),
    tagText = rs.get(dmm.tagText),
    paymentGap = rs.get(dmm.paymentGap),
    ageGap = rs.get(dmm.ageGap),
    starred = rs.get(dmm.starred),
    createdAt = rs.get(dmm.createdAt),
    updatedAt = rs.get(dmm.updatedAt),
    lockVersion = rs.get(dmm.lockVersion),
    createdUser = rs.get(dmm.createdUser),
    updatedUser = rs.get(dmm.updatedUser),
    deletedAt = rs.get(dmm.deletedAt),
    deleted = rs.get(dmm.deleted)
  )
      
  val dmm = DeliveryMailMatch.syntax("dmm")

  override val autoSession = AutoSession

  def find(id: Long)(implicit session: DBSession): Option[DeliveryMailMatch] = {
    withSQL {
      select.from(DeliveryMailMatch as dmm).where.eq(dmm.id, id)
    }.map(DeliveryMailMatch(dmm.resultName)).single.apply()
  }
          
  def findAll()(implicit session: DBSession): List[DeliveryMailMatch] = {
    withSQL(select.from(DeliveryMailMatch as dmm)).map(DeliveryMailMatch(dmm.resultName)).list.apply()
  }
          
  def countAll()(implicit session: DBSession): Long = {
    withSQL(select(sqls"count(1)").from(DeliveryMailMatch as dmm)).map(rs => rs.long(1)).single.apply().get
  }
          
  def findAllBy(where: SQLSyntax)(implicit session: DBSession): List[DeliveryMailMatch] = {
    withSQL { 
      select.from(DeliveryMailMatch as dmm).where.append(sqls"${where}")
    }.map(DeliveryMailMatch(dmm.resultName)).list.apply()
  }
      
  def countBy(where: SQLSyntax)(implicit session: DBSession): Long = {
    withSQL { 
      select(sqls"count(1)").from(DeliveryMailMatch as dmm).where.append(sqls"${where}")
    }.map(_.long(1)).single.apply().get
  }
      
  def create(
    ownerId: Option[Long] = None,
    deliveryMailId: Long,
    importMailId: Long,
    deliveryMailMatchType: String,
    matchingUserId: Long,
    memo: Option[String] = None,
    tagText: Option[String] = None,
    paymentGap: Option[Double] = None,
    ageGap: Option[Int] = None,
    starred: Option[Int] = None,
    createdAt: DateTime,
    updatedAt: DateTime,
    lockVersion: Option[Long] = None,
    createdUser: Option[String] = None,
    updatedUser: Option[String] = None,
    deletedAt: Option[DateTime] = None,
    deleted: Option[Int] = None)(implicit session: DBSession): DeliveryMailMatch = {
    val generatedKey = withSQL {
      insert.into(DeliveryMailMatch).columns(
        column.ownerId,
        column.deliveryMailId,
        column.importMailId,
        column.deliveryMailMatchType,
        column.matchingUserId,
        column.memo,
        column.tagText,
        column.paymentGap,
        column.ageGap,
        column.starred,
        column.createdAt,
        column.updatedAt,
        column.lockVersion,
        column.createdUser,
        column.updatedUser,
        column.deletedAt,
        column.deleted
      ).values(
        ownerId,
        deliveryMailId,
        importMailId,
        deliveryMailMatchType,
        matchingUserId,
        memo,
        tagText,
        paymentGap,
        ageGap,
        starred,
        createdAt,
        updatedAt,
        lockVersion,
        createdUser,
        updatedUser,
        deletedAt,
        deleted
      )
    }.updateAndReturnGeneratedKey.apply()

    DeliveryMailMatch(
      id = generatedKey, 
      ownerId = ownerId,
      deliveryMailId = deliveryMailId,
      importMailId = importMailId,
      deliveryMailMatchType = deliveryMailMatchType,
      matchingUserId = matchingUserId,
      memo = memo,
      tagText = tagText,
      paymentGap = paymentGap,
      ageGap = ageGap,
      starred = starred,
      createdAt = createdAt,
      updatedAt = updatedAt,
      lockVersion = lockVersion,
      createdUser = createdUser,
      updatedUser = updatedUser,
      deletedAt = deletedAt,
      deleted = deleted)
  }

  def save(entity: DeliveryMailMatch)(implicit session: DBSession): DeliveryMailMatch = {
    withSQL {
      update(DeliveryMailMatch).set(
        column.id -> entity.id,
        column.ownerId -> entity.ownerId,
        column.deliveryMailId -> entity.deliveryMailId,
        column.importMailId -> entity.importMailId,
        column.deliveryMailMatchType -> entity.deliveryMailMatchType,
        column.matchingUserId -> entity.matchingUserId,
        column.memo -> entity.memo,
        column.tagText -> entity.tagText,
        column.paymentGap -> entity.paymentGap,
        column.ageGap -> entity.ageGap,
        column.starred -> entity.starred,
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
        
  def destroy(entity: DeliveryMailMatch)(implicit session: DBSession): Unit = {
    withSQL { delete.from(DeliveryMailMatch).where.eq(column.id, entity.id) }.update.apply()
  }
        
}
