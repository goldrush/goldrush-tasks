package jp.applicative.gr.models

import org.scalatest._
import org.joda.time._
import scalikejdbc.scalatest.AutoRollback
import scalikejdbc._

class ImportMailMatchSpec extends fixture.FlatSpec with Matchers with AutoRollback {
  val imm = ImportMailMatch.syntax("imm")

  behavior of "ImportMailMatch"

  it should "find by primary keys" in { implicit session =>
    val maybeFound = ImportMailMatch.find(1L)
    maybeFound.isDefined should be(true)
  }
  it should "find all records" in { implicit session =>
    val allResults = ImportMailMatch.findAll()
    allResults.size should be >(0)
  }
  it should "count all records" in { implicit session =>
    val count = ImportMailMatch.countAll()
    count should be >(0L)
  }
  it should "find by where clauses" in { implicit session =>
    val results = ImportMailMatch.findAllBy(sqls.eq(imm.id, 1L))
    results.size should be >(0)
  }
  it should "count by where clauses" in { implicit session =>
    val count = ImportMailMatch.countBy(sqls.eq(imm.id, 1L))
    count should be >(0L)
  }
  it should "create new record" in { implicit session =>
    val created = ImportMailMatch.create(bizOfferMailId = 1L, bpMemberMailId = 1L, receivedAt = DateTime.now, createdAt = DateTime.now, updatedAt = DateTime.now)
    created should not be(null)
  }
  it should "save a record" in { implicit session =>
    val entity = ImportMailMatch.findAll().head
    // TODO modify something
    val modified = entity
    val updated = ImportMailMatch.save(modified)
    updated should not equal(entity)
  }
  it should "destroy a record" in { implicit session =>
    val entity = ImportMailMatch.findAll().head
    ImportMailMatch.destroy(entity)
    val shouldBeNone = ImportMailMatch.find(1L)
    shouldBeNone.isDefined should be(false)
  }

}
        