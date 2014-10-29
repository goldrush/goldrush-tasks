package jp.applicative.gr.models

import org.scalatest._
import org.joda.time._
import scalikejdbc.scalatest.AutoRollback
import scalikejdbc._

class DeliveryMailMatchSpec extends fixture.FlatSpec with Matchers with AutoRollback {
  val dmm = DeliveryMailMatch.syntax("dmm")

  behavior of "DeliveryMailMatch"

  it should "find by primary keys" in { implicit session =>
    val maybeFound = DeliveryMailMatch.find(1L)
    maybeFound.isDefined should be(true)
  }
  it should "find all records" in { implicit session =>
    val allResults = DeliveryMailMatch.findAll()
    allResults.size should be >(0)
  }
  it should "count all records" in { implicit session =>
    val count = DeliveryMailMatch.countAll()
    count should be >(0L)
  }
  it should "find by where clauses" in { implicit session =>
    val results = DeliveryMailMatch.findAllBy(sqls.eq(dmm.id, 1L))
    results.size should be >(0)
  }
  it should "count by where clauses" in { implicit session =>
    val count = DeliveryMailMatch.countBy(sqls.eq(dmm.id, 1L))
    count should be >(0L)
  }
  it should "create new record" in { implicit session =>
    val created = DeliveryMailMatch.create(deliveryMailId = 1L, importMailId = 1L, deliveryMailMatchType = "MyString", matchingUserId = 1L, createdAt = DateTime.now, updatedAt = DateTime.now)
    created should not be(null)
  }
  it should "save a record" in { implicit session =>
    val entity = DeliveryMailMatch.findAll().head
    // TODO modify something
    val modified = entity
    val updated = DeliveryMailMatch.save(modified)
    updated should not equal(entity)
  }
  it should "destroy a record" in { implicit session =>
    val entity = DeliveryMailMatch.findAll().head
    DeliveryMailMatch.destroy(entity)
    val shouldBeNone = DeliveryMailMatch.find(1L)
    shouldBeNone.isDefined should be(false)
  }

}
        