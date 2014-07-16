package jp.applicative.gr.models

import org.scalatest._
import org.joda.time._
import scalikejdbc.scalatest.AutoRollback
import scalikejdbc._

class ImportMailSpec extends fixture.FlatSpec with Matchers with AutoRollback {
  val im = ImportMail.syntax("im")

  behavior of "ImportMail"

  it should "find by primary keys" in { implicit session =>
    val maybeFound = ImportMail.find(1L)
    maybeFound.isDefined should be(true)
  }
  it should "find all records" in { implicit session =>
    val allResults = ImportMail.findAll()
    allResults.size should be >(0)
  }
  it should "count all records" in { implicit session =>
    val count = ImportMail.countAll()
    count should be >(0L)
  }
  it should "find by where clauses" in { implicit session =>
    val results = ImportMail.findAllBy(sqls.eq(im.id, 1L))
    results.size should be >(0)
  }
  it should "count by where clauses" in { implicit session =>
    val count = ImportMail.countBy(sqls.eq(im.id, 1L))
    count should be >(0L)
  }
  it should "create new record" in { implicit session =>
    val created = ImportMail.create(receivedAt = DateTime.now, mailSubject = "MyString", mailBody = "MyString", mailFrom = "MyString", mailSenderName = "MyString", matchingWayType = "MyString", createdAt = DateTime.now, updatedAt = DateTime.now)
    created should not be(null)
  }
  it should "save a record" in { implicit session =>
    val entity = ImportMail.findAll().head
    // TODO modify something
    val modified = entity
    val updated = ImportMail.save(modified)
    updated should not equal(entity)
  }
  it should "destroy a record" in { implicit session =>
    val entity = ImportMail.findAll().head
    ImportMail.destroy(entity)
    val shouldBeNone = ImportMail.find(1L)
    shouldBeNone.isDefined should be(false)
  }

}
        