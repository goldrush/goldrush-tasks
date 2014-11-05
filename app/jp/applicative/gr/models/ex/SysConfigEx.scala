package jp.applicative.gr.models.ex

import scalikejdbc._
import org.joda.time.{ DateTime, DateTimeZone }
import jp.applicative.gr.models.SysConfig

class SysConfigEx(val owner_id: Option[Long]) extends OwnerIdable[SysConfig] {

  val sc = SysConfig.syntax("sc")
  val q = sc

  def findLastId(implicit session: DBSession): Option[SysConfig] = findSectionKey("import_mail_matches", "last_id")
  def findSectionKey(section: String, key: String)(implicit session: DBSession): Option[SysConfig] = {
    withSQL {
      select.from(SysConfig as sc).where.append(_sqls).and.eq(sc.configSection, section).and.eq(sc.configKey, key).and.eq(sc.deleted, 0)
    }.map(SysConfig(sc.resultName)).single.apply()
  }

  def lastId(implicit session: DBSession): Option[Long] = {
    findLastId.flatMap(x => x.value1).map(_.toLong)
  }

  def importMailTargetDays(implicit session: DBSession): Int = findSectionKey("import_mail_matches", "target_days").flatMap(_.value1.map(_.toInt)).getOrElse(3)

  def deliveryMailTargetDays(implicit session: DBSession): Int = findSectionKey("delivery_mail_matches", "target_days").flatMap(_.value1.map(_.toInt)).getOrElse(3)

  def createOrUpdateLastId(last_id: Long)(implicit session: DBSession) {
    findLastId match {
      case Some(conf) =>
        SysConfig.save(
          SysConfig(
            id = conf.id,
            ownerId = conf.ownerId,
            configSection = conf.configSection,
            configKey = conf.configKey,
            value1 = Some(last_id.toString),
            value2 = conf.value2,
            value3 = conf.value3,
            configDescriptionText = conf.configDescriptionText,
            createdAt = conf.createdAt,
            updatedAt = new DateTime(DateTimeZone.UTC),
            lockVersion = conf.lockVersion.map(_ + 1),
            createdUser = conf.createdUser,
            updatedUser = Some("MailMatching"),
            deletedAt = conf.deletedAt,
            deleted = conf.deleted))
      case None => createLastId(last_id)
    }
  }

  def createLastId(last_id: Long)(implicit session: DBSession) {
    val now = new DateTime(DateTimeZone.UTC)
    SysConfig.create(
      ownerId = owner_id,
      configSection = "import_mail_matches",
      configKey = "last_id",
      value1 = Some(last_id.toString),
      value2 = None,
      value3 = None,
      configDescriptionText = None,
      createdAt = now,
      updatedAt = now,
      lockVersion = Some(0),
      createdUser = Some("MailMaching"),
      updatedUser = Some("MailMaching"),
      deletedAt = None,
      deleted = Some(0))
  }

}