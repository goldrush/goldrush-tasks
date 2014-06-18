package jp.applicative.gr.models.ex

import scalikejdbc._
import org.joda.time.{DateTime}
import jp.applicative.gr.models.ImportMail

object ImportMailEx {

  val im = ImportMail.syntax("im")

  def findBizOffers(last_id:Long, now_last_id: Long)(implicit session: DBSession) = ImportMail.findAllBy(sqls.gt(im.id, last_id).and.le(im.id, now_last_id).and.eq(im.deleted, 0).and.eq(im.bizOfferFlg, 1).orderBy(im.id).desc.limit(100))
  def findBpMembers(last_id:Long, now_last_id: Long)(implicit session: DBSession) = ImportMail.findAllBy(sqls.gt(im.id, last_id).and.le(im.id, now_last_id).and.eq(im.deleted, 0).and.eq(im.bpMemberFlg, 1).orderBy(im.id).desc.limit(100))

  def findBizOfferTargets(days: Int) = ImportMail.findAllBy(sqls.eq(im.deleted, 0).and.eq(im.bizOfferFlg, 1).and.gt(im.createdAt, (new DateTime()).minusDays(days)))
  def findBpMemberTargets(days: Int) = ImportMail.findAllBy(sqls.eq(im.deleted, 0).and.eq(im.bpMemberFlg, 1).and.gt(im.createdAt, (new DateTime()).minusDays(days)))

  def findLastId()(implicit session: DBSession): Option[Long] = { 
    withSQL { 
	  select.from(ImportMail as im).where.eq(im.deleted, 0).orderBy(im.id).desc.limit(1)
    }.map(ImportMail(im.resultName)).single.apply().map(_.id)
  }
  
}