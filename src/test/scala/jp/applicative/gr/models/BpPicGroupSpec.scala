package jp.applicative.gr.models

import org.scalatest._
import org.joda.time._
import scalikejdbc.scalatest.AutoRollback
import scalikejdbc._

class BpPicGroupSpec extends fixture.FlatSpec with Matchers with AutoRollback {
  val bpg = BpPicGroup.syntax("bpg")

  behavior of "BpPicGroup"

  it should "find by primary keys" in { implicit session =>
    val maybeFound = BpPicGroup.find(1L)
    maybeFound.isDefined should be(true)
  }
  it should "find all records" in { implicit session =>
    val allResults = BpPicGroup.findAll()
    allResults.size should be >(0)
  }
  it should "count all records" in { implicit session =>
    val count = BpPicGroup.countAll()
    count should be >(0L)
  }
  it should "find by where clauses" in { implicit session =>
    val results = BpPicGroup.findAllBy(sqls.eq(bpg.id, 1L))
    results.size should be >(0)
  }
  it should "count by where clauses" in { implicit session =>
    val count = BpPicGroup.countBy(sqls.eq(bpg.id, 1L))
    count should be >(0L)
  }
  it should "create new record" in { implicit session =>
    val created = BpPicGroup.create(bpPicGroupName = "MyString", bpPicGroupType = "MyString", createdAt = DateTime.now, updatedAt = DateTime.now)
    created should not be(null)
  }
  it should "save a record" in { implicit session =>
    val entity = BpPicGroup.findAll().head
    // TODO modify something
    val modified = entity
    val updated = BpPicGroup.save(modified)
    updated should not equal(entity)
  }
  it should "destroy a record" in { implicit session =>
    val entity = BpPicGroup.findAll().head
    BpPicGroup.destroy(entity)
    val shouldBeNone = BpPicGroup.find(1L)
    shouldBeNone.isDefined should be(false)
  }

}
        