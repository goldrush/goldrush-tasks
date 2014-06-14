package jp.applicative.gr.models

import scalikejdbc._
import org.joda.time.{DateTime}

object ImportMailEx {

  val im = ImportMail.syntax("im")

  def findBizOffers() = ImportMail.findAllBy(sqls.eq(im.bizOfferFlg, 1).and.gt(im.createdAt, (new DateTime()).minusDays(3)))
  
}