package jp.applicative.gr.models

import scalikejdbc._
import org.joda.time.{DateTime}

case class Tag(
  id: Long, 
  ownerId: Option[Long] = None, 
  tagKey: String, 
  tagText: String, 
  tagCount: Option[Long] = None, 
  incCount: Option[Long] = None, 
  tagLevel: Option[Int] = None, 
  displayOrder1: Option[Long] = None, 
  displayOrder2: Option[Long] = None, 
  displayOrder3: Option[Long] = None, 
  starred: Option[Int] = None, 
  createdAt: DateTime, 
  updatedAt: DateTime, 
  lockVersion: Option[Long] = None, 
  createdUser: Option[String] = None, 
  updatedUser: Option[String] = None, 
  deletedAt: Option[DateTime] = None, 
  deleted: Option[Int] = None) {

  def save()(implicit session: DBSession): Tag = Tag.save(this)(session)

  def destroy()(implicit session: DBSession): Unit = Tag.destroy(this)(session)

}
      

object Tag extends SQLSyntaxSupport[Tag] {

  override val tableName = "tags"

  override val columns = Seq("id", "owner_id", "tag_key", "tag_text", "tag_count", "inc_count", "tag_level", "display_order1", "display_order2", "display_order3", "starred", "created_at", "updated_at", "lock_version", "created_user", "updated_user", "deleted_at", "deleted")

  def apply(t: SyntaxProvider[Tag])(rs: WrappedResultSet): Tag = apply(t.resultName)(rs)
  def apply(t: ResultName[Tag])(rs: WrappedResultSet): Tag = new Tag(
    id = rs.get(t.id),
    ownerId = rs.get(t.ownerId),
    tagKey = rs.get(t.tagKey),
    tagText = rs.get(t.tagText),
    tagCount = rs.get(t.tagCount),
    incCount = rs.get(t.incCount),
    tagLevel = rs.get(t.tagLevel),
    displayOrder1 = rs.get(t.displayOrder1),
    displayOrder2 = rs.get(t.displayOrder2),
    displayOrder3 = rs.get(t.displayOrder3),
    starred = rs.get(t.starred),
    createdAt = rs.get(t.createdAt),
    updatedAt = rs.get(t.updatedAt),
    lockVersion = rs.get(t.lockVersion),
    createdUser = rs.get(t.createdUser),
    updatedUser = rs.get(t.updatedUser),
    deletedAt = rs.get(t.deletedAt),
    deleted = rs.get(t.deleted)
  )
      
  val t = Tag.syntax("t")

  override val autoSession = AutoSession

  def find(id: Long)(implicit session: DBSession): Option[Tag] = {
    withSQL {
      select.from(Tag as t).where.eq(t.id, id)
    }.map(Tag(t.resultName)).single.apply()
  }
          
  def findAll()(implicit session: DBSession): List[Tag] = {
    withSQL(select.from(Tag as t)).map(Tag(t.resultName)).list.apply()
  }
          
  def countAll()(implicit session: DBSession): Long = {
    withSQL(select(sqls"count(1)").from(Tag as t)).map(rs => rs.long(1)).single.apply().get
  }
          
  def findAllBy(where: SQLSyntax)(implicit session: DBSession): List[Tag] = {
    withSQL { 
      select.from(Tag as t).where.append(sqls"${where}")
    }.map(Tag(t.resultName)).list.apply()
  }
      
  def countBy(where: SQLSyntax)(implicit session: DBSession): Long = {
    withSQL { 
      select(sqls"count(1)").from(Tag as t).where.append(sqls"${where}")
    }.map(_.long(1)).single.apply().get
  }
      
  def create(
    ownerId: Option[Long] = None,
    tagKey: String,
    tagText: String,
    tagCount: Option[Long] = None,
    incCount: Option[Long] = None,
    tagLevel: Option[Int] = None,
    displayOrder1: Option[Long] = None,
    displayOrder2: Option[Long] = None,
    displayOrder3: Option[Long] = None,
    starred: Option[Int] = None,
    createdAt: DateTime,
    updatedAt: DateTime,
    lockVersion: Option[Long] = None,
    createdUser: Option[String] = None,
    updatedUser: Option[String] = None,
    deletedAt: Option[DateTime] = None,
    deleted: Option[Int] = None)(implicit session: DBSession): Tag = {
    val generatedKey = withSQL {
      insert.into(Tag).columns(
        column.ownerId,
        column.tagKey,
        column.tagText,
        column.tagCount,
        column.incCount,
        column.tagLevel,
        column.displayOrder1,
        column.displayOrder2,
        column.displayOrder3,
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
        tagKey,
        tagText,
        tagCount,
        incCount,
        tagLevel,
        displayOrder1,
        displayOrder2,
        displayOrder3,
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

    Tag(
      id = generatedKey, 
      ownerId = ownerId,
      tagKey = tagKey,
      tagText = tagText,
      tagCount = tagCount,
      incCount = incCount,
      tagLevel = tagLevel,
      displayOrder1 = displayOrder1,
      displayOrder2 = displayOrder2,
      displayOrder3 = displayOrder3,
      starred = starred,
      createdAt = createdAt,
      updatedAt = updatedAt,
      lockVersion = lockVersion,
      createdUser = createdUser,
      updatedUser = updatedUser,
      deletedAt = deletedAt,
      deleted = deleted)
  }

  def save(entity: Tag)(implicit session: DBSession): Tag = {
    withSQL {
      update(Tag).set(
        column.id -> entity.id,
        column.ownerId -> entity.ownerId,
        column.tagKey -> entity.tagKey,
        column.tagText -> entity.tagText,
        column.tagCount -> entity.tagCount,
        column.incCount -> entity.incCount,
        column.tagLevel -> entity.tagLevel,
        column.displayOrder1 -> entity.displayOrder1,
        column.displayOrder2 -> entity.displayOrder2,
        column.displayOrder3 -> entity.displayOrder3,
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
        
  def destroy(entity: Tag)(implicit session: DBSession): Unit = {
    withSQL { delete.from(Tag).where.eq(column.id, entity.id) }.update.apply()
  }
        
}
