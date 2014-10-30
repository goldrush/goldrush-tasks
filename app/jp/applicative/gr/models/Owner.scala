package jp.applicative.gr.models

import scalikejdbc._
import org.joda.time.{DateTime}

case class Owner(
  id: Long, 
  ownerKey: String, 
  companyName: Option[String] = None, 
  additionalOption: Option[String] = None, 
  createdAt: DateTime, 
  updatedAt: DateTime, 
  lockVersion: Option[Long] = None, 
  createdUser: Option[String] = None, 
  updatedUser: Option[String] = None, 
  deletedAt: Option[DateTime] = None, 
  deleted: Option[Int] = None) {

  def save()(implicit session: DBSession): Owner = Owner.save(this)(session)

  def destroy()(implicit session: DBSession): Unit = Owner.destroy(this)(session)

}
      

object Owner extends SQLSyntaxSupport[Owner] {

  override val tableName = "owners"

  override val columns = Seq("id", "owner_key", "company_name", "additional_option", "created_at", "updated_at", "lock_version", "created_user", "updated_user", "deleted_at", "deleted")

  def apply(o: SyntaxProvider[Owner])(rs: WrappedResultSet): Owner = apply(o.resultName)(rs)
  def apply(o: ResultName[Owner])(rs: WrappedResultSet): Owner = new Owner(
    id = rs.get(o.id),
    ownerKey = rs.get(o.ownerKey),
    companyName = rs.get(o.companyName),
    additionalOption = rs.get(o.additionalOption),
    createdAt = rs.get(o.createdAt),
    updatedAt = rs.get(o.updatedAt),
    lockVersion = rs.get(o.lockVersion),
    createdUser = rs.get(o.createdUser),
    updatedUser = rs.get(o.updatedUser),
    deletedAt = rs.get(o.deletedAt),
    deleted = rs.get(o.deleted)
  )
      
  val o = Owner.syntax("o")

  override val autoSession = AutoSession

  def find(id: Long)(implicit session: DBSession): Option[Owner] = {
    withSQL {
      select.from(Owner as o).where.eq(o.id, id)
    }.map(Owner(o.resultName)).single.apply()
  }
          
  def findAll()(implicit session: DBSession): List[Owner] = {
    withSQL(select.from(Owner as o)).map(Owner(o.resultName)).list.apply()
  }
          
  def countAll()(implicit session: DBSession): Long = {
    withSQL(select(sqls"count(1)").from(Owner as o)).map(rs => rs.long(1)).single.apply().get
  }
          
  def findAllBy(where: SQLSyntax)(implicit session: DBSession): List[Owner] = {
    withSQL { 
      select.from(Owner as o).where.append(sqls"${where}")
    }.map(Owner(o.resultName)).list.apply()
  }
      
  def countBy(where: SQLSyntax)(implicit session: DBSession): Long = {
    withSQL { 
      select(sqls"count(1)").from(Owner as o).where.append(sqls"${where}")
    }.map(_.long(1)).single.apply().get
  }
      
  def create(
    ownerKey: String,
    companyName: Option[String] = None,
    additionalOption: Option[String] = None,
    createdAt: DateTime,
    updatedAt: DateTime,
    lockVersion: Option[Long] = None,
    createdUser: Option[String] = None,
    updatedUser: Option[String] = None,
    deletedAt: Option[DateTime] = None,
    deleted: Option[Int] = None)(implicit session: DBSession): Owner = {
    val generatedKey = withSQL {
      insert.into(Owner).columns(
        column.ownerKey,
        column.companyName,
        column.additionalOption,
        column.createdAt,
        column.updatedAt,
        column.lockVersion,
        column.createdUser,
        column.updatedUser,
        column.deletedAt,
        column.deleted
      ).values(
        ownerKey,
        companyName,
        additionalOption,
        createdAt,
        updatedAt,
        lockVersion,
        createdUser,
        updatedUser,
        deletedAt,
        deleted
      )
    }.updateAndReturnGeneratedKey.apply()

    Owner(
      id = generatedKey, 
      ownerKey = ownerKey,
      companyName = companyName,
      additionalOption = additionalOption,
      createdAt = createdAt,
      updatedAt = updatedAt,
      lockVersion = lockVersion,
      createdUser = createdUser,
      updatedUser = updatedUser,
      deletedAt = deletedAt,
      deleted = deleted)
  }

  def save(entity: Owner)(implicit session: DBSession): Owner = {
    withSQL {
      update(Owner).set(
        column.id -> entity.id,
        column.ownerKey -> entity.ownerKey,
        column.companyName -> entity.companyName,
        column.additionalOption -> entity.additionalOption,
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
        
  def destroy(entity: Owner)(implicit session: DBSession): Unit = {
    withSQL { delete.from(Owner).where.eq(column.id, entity.id) }.update.apply()
  }
        
}
