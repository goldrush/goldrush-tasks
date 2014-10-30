package jp.applicative.gr.models

import org.scalatest._
import org.joda.time._
import scalikejdbc.scalatest.AutoRollback
import scalikejdbc._

class OwnerSpec extends fixture.FlatSpec with Matchers with AutoRollback {
  val o = Owner.syntax("o")

  behavior of "Owner"

  it should "find by primary keys" in { implicit session =>
    val maybeFound = Owner.find(1L)
    maybeFound.isDefined should be(true)
  }
  it should "find all records" in { implicit session =>
    val allResults = Owner.findAll()
    allResults.size should be >(0)
  }
  it should "count all records" in { implicit session =>
    val count = Owner.countAll()
    count should be >(0L)
  }
  it should "find by where clauses" in { implicit session =>
    val results = Owner.findAllBy(sqls.eq(o.id, 1L))
    results.size should be >(0)
  }
  it should "count by where clauses" in { implicit session =>
    val count = Owner.countBy(sqls.eq(o.id, 1L))
    count should be >(0L)
  }
  it should "create new record" in { implicit session =>
    val created = Owner.create(ownerKey = "MyString", createdAt = DateTime.now, updatedAt = DateTime.now)
    created should not be(null)
  }
  it should "save a record" in { implicit session =>
    val entity = Owner.findAll().head
    // TODO modify something
    val modified = entity
    val updated = Owner.save(modified)
    updated should not equal(entity)
  }
  it should "destroy a record" in { implicit session =>
    val entity = Owner.findAll().head
    Owner.destroy(entity)
    val shouldBeNone = Owner.find(1L)
    shouldBeNone.isDefined should be(false)
  }

}
        