package jp.applicative.gr.models

import org.scalatest._
import org.joda.time._
import scalikejdbc.scalatest.AutoRollback
import scalikejdbc._

class SysConfigSpec extends fixture.FlatSpec with Matchers with AutoRollback {
  val sc = SysConfig.syntax("sc")

  behavior of "SysConfig"

  it should "find by primary keys" in { implicit session =>
    val maybeFound = SysConfig.find(1L)
    maybeFound.isDefined should be(true)
  }
  it should "find all records" in { implicit session =>
    val allResults = SysConfig.findAll()
    allResults.size should be >(0)
  }
  it should "count all records" in { implicit session =>
    val count = SysConfig.countAll()
    count should be >(0L)
  }
  it should "find by where clauses" in { implicit session =>
    val results = SysConfig.findAllBy(sqls.eq(sc.id, 1L))
    results.size should be >(0)
  }
  it should "count by where clauses" in { implicit session =>
    val count = SysConfig.countBy(sqls.eq(sc.id, 1L))
    count should be >(0L)
  }
  it should "create new record" in { implicit session =>
    val created = SysConfig.create(configSection = "MyString", configKey = "MyString", createdAt = DateTime.now, updatedAt = DateTime.now)
    created should not be(null)
  }
  it should "save a record" in { implicit session =>
    val entity = SysConfig.findAll().head
    // TODO modify something
    val modified = entity
    val updated = SysConfig.save(modified)
    updated should not equal(entity)
  }
  it should "destroy a record" in { implicit session =>
    val entity = SysConfig.findAll().head
    SysConfig.destroy(entity)
    val shouldBeNone = SysConfig.find(1L)
    shouldBeNone.isDefined should be(false)
  }

}
        