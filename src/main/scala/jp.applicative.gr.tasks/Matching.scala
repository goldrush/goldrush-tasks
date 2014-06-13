package jp.applicative.gr.tasks

import scalikejdbc._

class Matching {
  implicit val session = AutoSession
  
  case class ImportMailMatch(biz_offer_mail_id: Long, bp_member_mail_id: Long, tags: Seq[String], match_score: Int) {
    
  }
  def list() = {
	  val x: List[Map[String, Any]] = sql"select * from import_mails order by id desc limit 2".map(_.toMap).list.apply
	  x
  }
}