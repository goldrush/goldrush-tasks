package jp.applicative.gr.tasks

import scalikejdbc._
import jp.applicative.gr.models._
import jp.applicative.gr.models.ex._
import org.joda.time.DateTime
import scalikejdbc.AutoSession
import org.joda.time.DateTimeZone

class Matching {
  implicit val session = AutoSession
  
  val goodTags = TagEx.goodTagSet
  val veryGoodTags = TagEx.veryGoodTagSet
  
  def matching() {
    SysConfigEx.lastId match {
      case None => ImportMailEx.findLastId.map{ last_id =>
      	SysConfigEx.createLastId(last_id)
	    println("初めての実行です。過去分を無視します")
      }
      case Some(last_id) =>
        ImportMailEx.findLastId.map{ now_last_id =>
          	val days = SysConfigEx.targetDays
		    val bizList = ImportMailEx.findBizOffers(last_id, now_last_id)
		    if(bizList.nonEmpty) {
			    val bpmTargetList = ImportMailEx.findBpMemberTargets(last_id, days)
			    matching_in(bizList, bpmTargetList)
		    }
		    val bpmList = ImportMailEx.findBpMembers(last_id, now_last_id)
		    if(bpmList.nonEmpty) {
		    	val bizTargetList = ImportMailEx.findBizOfferTargets(last_id, days)
		    	matching_in(bizTargetList, bpmList)
		    }
		    
     		SysConfigEx.createOrUpdateLastId(now_last_id)
        }
    }
  }
  
  def matching_in(bizList: List[ImportMail], bpmList: List[ImportMail]) {
    for {
      biz <- bizList
      bpm <- bpmList
      if biz.id != bpm.id // 別メールなら
      if !(biz.businessPartnerId.nonEmpty && bpm.businessPartnerId.nonEmpty && biz.businessPartnerId == bpm.businessPartnerId) // 別会社なら
    }yield{
      biz.tagText.map{z:String =>
      	bpm.tagText.map{m:String =>
      		val checked = check(
      		    z.split(",").map(_.toLowerCase()).toList.sorted.distinct,
      		    m.split(",").map(_.toLowerCase()).toList.sorted.distinct)
      		val p = point(checked)
      		
      		if(p > 0) {
	      		val now = new DateTime(DateTimeZone.UTC)
	      		val imm = ImportMailMatch.create(
				    ownerId = None,
				    bizOfferMailId = biz.id,
				    bpMemberMailId = bpm.id,
				    mailMatchScore = Some(p),
  				    tagText = Some(checked.mkString(",")), 
	  			    paymentGap = biz.payment.flatMap(x => bpm.payment.map(y => x - y)), 
		  		    ageGap = biz.age.flatMap(x => bpm.age.map(y => x - y)), 
				    createdAt = now,
				    updatedAt = now,
				    lockVersion = Some(0),
				    createdUser = Some("MailMaching"),
				    updatedUser = Some("MailMaching"),
				    deletedAt = None,
				    deleted = Some(0))
      		}
      	}
      }
    }
  }
  
  def point(list: List[String]):Int = list.map{ tag =>
  	if(goodTags.contains(tag)){
  	  4
  	}else if(veryGoodTags.contains(tag)){
  	  5
  	}else {
  	  0
  	}
  }.sum
  
  def check(x: List[String], y: List[String]): List[String] = {
    (x, y) match {
      case (List(), _) => List()
      case (_, List()) => List()
      case (x, y) =>
	    if (x.head == y.head) {
	      x.head :: check(x.tail, y.tail)
	    } else if (x.head.compareTo(y.head) > 0) {
	      check(x, y.tail)
	    } else {
	      check(x.tail, y)
	    }
	}
  }
  
  def point(x: String) = 5
}