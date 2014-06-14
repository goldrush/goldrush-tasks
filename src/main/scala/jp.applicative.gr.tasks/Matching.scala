package jp.applicative.gr.tasks

import scalikejdbc._
import jp.applicative.gr.models._

class Matching {
  implicit val session = AutoSession
  
  case class ImportMailMatch(biz_offer_mail_id: Long, bp_member_mail_id: Long, tags: Seq[String], match_score: Int) {
    
  }
  def list() = {
	  val x: List[ImportMail] = ImportMailEx.findBizOffers()
	  x
  }
}