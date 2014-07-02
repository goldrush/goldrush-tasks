package jp.applicative.gr.tasks

import org.slf4j.LoggerFactory
import scalikejdbc.AutoSession
import jp.applicative.gr.models.ex.TagEx

class MatchingUtil {

  private val log = LoggerFactory.getLogger(this.getClass())

  implicit val session = AutoSession
  
  val goodTags = TagEx.goodTagSet
  val veryGoodTags = TagEx.veryGoodTagSet
  
  val plu = PluralAnalyzer.default // TODO: sys_configs
  
  def isPlural(body: String): Boolean = plu.isPlural(body)

  def point(list: List[String]):Int = list.map{ tag =>
  	if(goodTags.contains(tag)){
  	  4
  	}else if(veryGoodTags.contains(tag)){
  	  5
  	}else {
  	  0
  	}
  }.sum
  
  def check(x: String, y: String): List[String] = {
    def check_in(as: List[String], bs: List[String]): List[String] = {
     (as, bs) match {
      case (List(), _) => List()
      case (_, List()) => List()
      case (a, b) =>
	    if (a.head == b.head) {
	      a.head :: check_in(a.tail, b.tail)
	    } else if (a.head.compareTo(b.head) > 0) {
	      check_in(a, b.tail)
	    } else {
	      check_in(a.tail, b)
	    }
	}
    }
    check_in(x.split(",").map(_.toLowerCase()).toList.sorted.distinct,
     y.split(",").map(_.toLowerCase()).toList.sorted.distinct)
  }
 
}