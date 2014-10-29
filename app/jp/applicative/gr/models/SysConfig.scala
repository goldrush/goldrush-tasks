package jp.applicative.gr.models

import scalikejdbc._
import org.joda.time.{DateTime}

case class SysConfig(
  id: Long, 
  ownerId: Option[Long] = None, 
  configSection: String, 
  configKey: String, 
  value1: Option[String] = None, 
  value2: Option[String] = None, 
  value3: Option[String] = None, 
  configDescriptionText: Option[String] = None, 
  createdAt: DateTime, 
  updatedAt: DateTime, 
  lockVersion: Option[Long] = None, 
  createdUser: Option[String] = None, 
  updatedUser: Option[String] = None, 
  deletedAt: Option[DateTime] = None, 
  deleted: Option[Int] = None) {

  def save()(implicit session: DBSession): SysConfig = SysConfig.save(this)(session)

  def destroy()(implicit session: DBSession): Unit = SysConfig.destroy(this)(session)

}
      

object SysConfig extends SQLSyntaxSupport[SysConfig] {

  override val tableName = "sys_configs"

  override val columns = Seq("id", "owner_id", "config_section", "config_key", "value1", "value2", "value3", "config_description_text", "created_at", "updated_at", "lock_version", "created_user", "updated_user", "deleted_at", "deleted")

  def apply(sc: SyntaxProvider[SysConfig])(rs: WrappedResultSet): SysConfig = apply(sc.resultName)(rs)
  def apply(sc: ResultName[SysConfig])(rs: WrappedResultSet): SysConfig = new SysConfig(
    id = rs.get(sc.id),
    ownerId = rs.get(sc.ownerId),
    configSection = rs.get(sc.configSection),
    configKey = rs.get(sc.configKey),
    value1 = rs.get(sc.value1),
    value2 = rs.get(sc.value2),
    value3 = rs.get(sc.value3),
    configDescriptionText = rs.get(sc.configDescriptionText),
    createdAt = rs.get(sc.createdAt),
    updatedAt = rs.get(sc.updatedAt),
    lockVersion = rs.get(sc.lockVersion),
    createdUser = rs.get(sc.createdUser),
    updatedUser = rs.get(sc.updatedUser),
    deletedAt = rs.get(sc.deletedAt),
    deleted = rs.get(sc.deleted)
  )
      
  val sc = SysConfig.syntax("sc")

  override val autoSession = AutoSession

  def find(id: Long)(implicit session: DBSession): Option[SysConfig] = {
    withSQL {
      select.from(SysConfig as sc).where.eq(sc.id, id)
    }.map(SysConfig(sc.resultName)).single.apply()
  }
          
  def findAll()(implicit session: DBSession): List[SysConfig] = {
    withSQL(select.from(SysConfig as sc)).map(SysConfig(sc.resultName)).list.apply()
  }
          
  def countAll()(implicit session: DBSession): Long = {
    withSQL(select(sqls"count(1)").from(SysConfig as sc)).map(rs => rs.long(1)).single.apply().get
  }
          
  def findAllBy(where: SQLSyntax)(implicit session: DBSession): List[SysConfig] = {
    withSQL { 
      select.from(SysConfig as sc).where.append(sqls"${where}")
    }.map(SysConfig(sc.resultName)).list.apply()
  }
      
  def countBy(where: SQLSyntax)(implicit session: DBSession): Long = {
    withSQL { 
      select(sqls"count(1)").from(SysConfig as sc).where.append(sqls"${where}")
    }.map(_.long(1)).single.apply().get
  }
      
  def create(
    ownerId: Option[Long] = None,
    configSection: String,
    configKey: String,
    value1: Option[String] = None,
    value2: Option[String] = None,
    value3: Option[String] = None,
    configDescriptionText: Option[String] = None,
    createdAt: DateTime,
    updatedAt: DateTime,
    lockVersion: Option[Long] = None,
    createdUser: Option[String] = None,
    updatedUser: Option[String] = None,
    deletedAt: Option[DateTime] = None,
    deleted: Option[Int] = None)(implicit session: DBSession): SysConfig = {
    val generatedKey = withSQL {
      insert.into(SysConfig).columns(
        column.ownerId,
        column.configSection,
        column.configKey,
        column.value1,
        column.value2,
        column.value3,
        column.configDescriptionText,
        column.createdAt,
        column.updatedAt,
        column.lockVersion,
        column.createdUser,
        column.updatedUser,
        column.deletedAt,
        column.deleted
      ).values(
        ownerId,
        configSection,
        configKey,
        value1,
        value2,
        value3,
        configDescriptionText,
        createdAt,
        updatedAt,
        lockVersion,
        createdUser,
        updatedUser,
        deletedAt,
        deleted
      )
    }.updateAndReturnGeneratedKey.apply()

    SysConfig(
      id = generatedKey, 
      ownerId = ownerId,
      configSection = configSection,
      configKey = configKey,
      value1 = value1,
      value2 = value2,
      value3 = value3,
      configDescriptionText = configDescriptionText,
      createdAt = createdAt,
      updatedAt = updatedAt,
      lockVersion = lockVersion,
      createdUser = createdUser,
      updatedUser = updatedUser,
      deletedAt = deletedAt,
      deleted = deleted)
  }

  def save(entity: SysConfig)(implicit session: DBSession): SysConfig = {
    withSQL {
      update(SysConfig).set(
        column.id -> entity.id,
        column.ownerId -> entity.ownerId,
        column.configSection -> entity.configSection,
        column.configKey -> entity.configKey,
        column.value1 -> entity.value1,
        column.value2 -> entity.value2,
        column.value3 -> entity.value3,
        column.configDescriptionText -> entity.configDescriptionText,
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
        
  def destroy(entity: SysConfig)(implicit session: DBSession): Unit = {
    withSQL { delete.from(SysConfig).where.eq(column.id, entity.id) }.update.apply()
  }
        
}
