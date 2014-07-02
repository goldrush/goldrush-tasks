package jp.applicative.gr.models

import scalikejdbc._
import org.joda.time.{DateTime}

case class BpPicGroup(
  id: Long, 
  ownerId: Option[Long] = None, 
  bpPicGroupName: String, 
  mailTemplateId: Option[Long] = None, 
  bpPicGroupType: String, 
  memo: Option[String] = None, 
  createdAt: DateTime, 
  updatedAt: DateTime, 
  lockVersion: Option[Long] = None, 
  createdUser: Option[String] = None, 
  updatedUser: Option[String] = None, 
  deletedAt: Option[DateTime] = None, 
  deleted: Option[Int] = None) {

  def save()(implicit session: DBSession = BpPicGroup.autoSession): BpPicGroup = BpPicGroup.save(this)(session)

  def destroy()(implicit session: DBSession = BpPicGroup.autoSession): Unit = BpPicGroup.destroy(this)(session)

}
      

object BpPicGroup extends SQLSyntaxSupport[BpPicGroup] {

  override val tableName = "bp_pic_groups"

  override val columns = Seq("id", "owner_id", "bp_pic_group_name", "mail_template_id", "bp_pic_group_type", "memo", "created_at", "updated_at", "lock_version", "created_user", "updated_user", "deleted_at", "deleted")

  def apply(bpg: SyntaxProvider[BpPicGroup])(rs: WrappedResultSet): BpPicGroup = apply(bpg.resultName)(rs)
  def apply(bpg: ResultName[BpPicGroup])(rs: WrappedResultSet): BpPicGroup = new BpPicGroup(
    id = rs.get(bpg.id),
    ownerId = rs.get(bpg.ownerId),
    bpPicGroupName = rs.get(bpg.bpPicGroupName),
    mailTemplateId = rs.get(bpg.mailTemplateId),
    bpPicGroupType = rs.get(bpg.bpPicGroupType),
    memo = rs.get(bpg.memo),
    createdAt = rs.get(bpg.createdAt),
    updatedAt = rs.get(bpg.updatedAt),
    lockVersion = rs.get(bpg.lockVersion),
    createdUser = rs.get(bpg.createdUser),
    updatedUser = rs.get(bpg.updatedUser),
    deletedAt = rs.get(bpg.deletedAt),
    deleted = rs.get(bpg.deleted)
  )
      
  val bpg = BpPicGroup.syntax("bpg")

  override val autoSession = AutoSession

  def find(id: Long)(implicit session: DBSession = autoSession): Option[BpPicGroup] = {
    withSQL {
      select.from(BpPicGroup as bpg).where.eq(bpg.id, id)
    }.map(BpPicGroup(bpg.resultName)).single.apply()
  }
          
  def findAll()(implicit session: DBSession = autoSession): List[BpPicGroup] = {
    withSQL(select.from(BpPicGroup as bpg)).map(BpPicGroup(bpg.resultName)).list.apply()
  }
          
  def countAll()(implicit session: DBSession = autoSession): Long = {
    withSQL(select(sqls"count(1)").from(BpPicGroup as bpg)).map(rs => rs.long(1)).single.apply().get
  }
          
  def findAllBy(where: SQLSyntax)(implicit session: DBSession = autoSession): List[BpPicGroup] = {
    withSQL { 
      select.from(BpPicGroup as bpg).where.append(sqls"${where}")
    }.map(BpPicGroup(bpg.resultName)).list.apply()
  }
      
  def countBy(where: SQLSyntax)(implicit session: DBSession = autoSession): Long = {
    withSQL { 
      select(sqls"count(1)").from(BpPicGroup as bpg).where.append(sqls"${where}")
    }.map(_.long(1)).single.apply().get
  }
      
  def create(
    ownerId: Option[Long] = None,
    bpPicGroupName: String,
    mailTemplateId: Option[Long] = None,
    bpPicGroupType: String,
    memo: Option[String] = None,
    createdAt: DateTime,
    updatedAt: DateTime,
    lockVersion: Option[Long] = None,
    createdUser: Option[String] = None,
    updatedUser: Option[String] = None,
    deletedAt: Option[DateTime] = None,
    deleted: Option[Int] = None)(implicit session: DBSession = autoSession): BpPicGroup = {
    val generatedKey = withSQL {
      insert.into(BpPicGroup).columns(
        column.ownerId,
        column.bpPicGroupName,
        column.mailTemplateId,
        column.bpPicGroupType,
        column.memo,
        column.createdAt,
        column.updatedAt,
        column.lockVersion,
        column.createdUser,
        column.updatedUser,
        column.deletedAt,
        column.deleted
      ).values(
        ownerId,
        bpPicGroupName,
        mailTemplateId,
        bpPicGroupType,
        memo,
        createdAt,
        updatedAt,
        lockVersion,
        createdUser,
        updatedUser,
        deletedAt,
        deleted
      )
    }.updateAndReturnGeneratedKey.apply()

    BpPicGroup(
      id = generatedKey, 
      ownerId = ownerId,
      bpPicGroupName = bpPicGroupName,
      mailTemplateId = mailTemplateId,
      bpPicGroupType = bpPicGroupType,
      memo = memo,
      createdAt = createdAt,
      updatedAt = updatedAt,
      lockVersion = lockVersion,
      createdUser = createdUser,
      updatedUser = updatedUser,
      deletedAt = deletedAt,
      deleted = deleted)
  }

  def save(entity: BpPicGroup)(implicit session: DBSession = autoSession): BpPicGroup = {
    withSQL {
      update(BpPicGroup).set(
        column.id -> entity.id,
        column.ownerId -> entity.ownerId,
        column.bpPicGroupName -> entity.bpPicGroupName,
        column.mailTemplateId -> entity.mailTemplateId,
        column.bpPicGroupType -> entity.bpPicGroupType,
        column.memo -> entity.memo,
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
        
  def destroy(entity: BpPicGroup)(implicit session: DBSession = autoSession): Unit = {
    withSQL { delete.from(BpPicGroup).where.eq(column.id, entity.id) }.update.apply()
  }
        
}
