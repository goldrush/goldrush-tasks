package jp.applicative.gr.models

import org.scalatest._
import org.joda.time._
import scalikejdbc.scalatest.AutoRollback
import scalikejdbc._

class DeliveryMailSpec extends fixture.FlatSpec with Matchers with AutoRollback {
  val dm = DeliveryMail.syntax("dm")

  behavior of "DeliveryMail"

  it should "find by primary keys" in { implicit session =>
    val maybeFound = DeliveryMail.find(1L)
    maybeFound.isDefined should be(true)
  }
  it should "find all records" in { implicit session =>
    val allResults = DeliveryMail.findAll()
    allResults.size should be >(0)
  }
  it should "count all records" in { implicit session =>
    val count = DeliveryMail.countAll()
    count should be >(0L)
  }
  it should "find by where clauses" in { implicit session =>
    val results = DeliveryMail.findAllBy(sqls.eq(dm.id, 1L))
    results.size should be >(0)
  }
  it should "count by where clauses" in { implicit session =>
    val count = DeliveryMail.countBy(sqls.eq(dm.id, 1L))
    count should be >(0L)
  }
  it should "create new record" in { implicit session =>
    val created = DeliveryMail.create(deliveryMailType = "MyString", mailStatusType = "MyString", subject = "MyString", content = "MyString", mailFromName = "MyString", mailFrom = "MyString", plannedSettingAt = DateTime.now, mailSendStatusType = "MyString", createdAt = DateTime.now, updatedAt = DateTime.now)
    created should not be(null)
  }
  it should "save a record" in { implicit session =>
    val entity = DeliveryMail.findAll().head
    // TODO modify something
    val modified = entity
    val updated = DeliveryMail.save(modified)
    updated should not equal(entity)
  }
  it should "destroy a record" in { implicit session =>
    val entity = DeliveryMail.findAll().head
    DeliveryMail.destroy(entity)
    val shouldBeNone = DeliveryMail.find(1L)
    shouldBeNone.isDefined should be(false)
  }

}
        