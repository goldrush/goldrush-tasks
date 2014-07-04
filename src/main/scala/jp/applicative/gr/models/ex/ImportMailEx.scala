package jp.applicative.gr.models.ex

import scalikejdbc._
import org.joda.time.{DateTime, DateTimeZone}
import jp.applicative.gr.models.ImportMail

object ImportMailEx {

  val im = ImportMail.syntax("im")

  def findBizOffers(last_id:Long, now_last_id: Long)(implicit session: DBSession) = ImportMail.findAllBy(sqls.gt(im.id, last_id).and.le(im.id, now_last_id).and.eq(im.deleted, 0).and.eq(im.bizOfferFlg, 1).orderBy(im.id).desc)
  def findBpMembers(last_id:Long, now_last_id: Long)(implicit session: DBSession) = ImportMail.findAllBy(sqls.gt(im.id, last_id).and.le(im.id, now_last_id).and.eq(im.deleted, 0).and.eq(im.bpMemberFlg, 1).orderBy(im.id).desc)

  def findBizOffersOrDays(last_id:Long, now_last_id: Long, days: Int)(implicit session: DBSession) = ImportMail.findAllBy(
      sqls.gt(im.id, last_id).and.le(im.id, now_last_id).and.eq(im.pluralFlg, 0).and.eq(im.deleted, 0).and.eq(im.bizOfferFlg, 1)
      .and.gt(im.createdAt, (new DateTime(DateTimeZone.UTC)).minusDays(days)).orderBy(im.id).desc)

  def findBpMembersOrDays(last_id:Long, now_last_id: Long, days: Int)(implicit session: DBSession) = ImportMail.findAllBy(
      sqls.gt(im.id, last_id).and.le(im.id, now_last_id).and.eq(im.pluralFlg, 0).and.eq(im.deleted, 0).and.eq(im.bpMemberFlg, 1)
      .and.gt(im.createdAt, (new DateTime(DateTimeZone.UTC)).minusDays(days)).orderBy(im.id).desc)

  def findBizOfferTargets(last_id:Long, days: Int)(implicit session: DBSession) = ImportMail.findAllBy(sqls.eq(im.deleted, 0)
      .and.eq(im.pluralFlg, 0)
      .and.le(im.id, last_id)
      .and.eq(im.bizOfferFlg, 1)
      .and.gt(im.createdAt, (new DateTime(DateTimeZone.UTC)).minusDays(days)))

  def findBpMemberTargets(last_id:Long, days: Int)(implicit session: DBSession) = ImportMail.findAllBy(sqls.eq(im.deleted, 0)
      .and.eq(im.pluralFlg, 0)
      .and.le(im.id, last_id)
      .and.eq(im.bpMemberFlg, 1)
      .and.gt(im.createdAt, (new DateTime(DateTimeZone.UTC)).minusDays(days)))

  def findAllByPage(page: Int)(implicit session: DBSession) = ImportMail.findAllBy(sqls.eq(im.deleted, 0)
      .orderBy(im.id)
      .limit(1000)
      .offset(page * 1000))
      
  def findLastId()(implicit session: DBSession): Option[Long] = { 
    withSQL { 
	  select.from(ImportMail as im).where.eq(im.deleted, 0).orderBy(im.id).desc.limit(1)
    }.map(ImportMail(im.resultName)).single.apply().map(_.id)
  }
  
}