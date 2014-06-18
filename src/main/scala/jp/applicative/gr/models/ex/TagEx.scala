package jp.applicative.gr.models.ex

import scalikejdbc._
import org.joda.time.{DateTime}
import jp.applicative.gr.models.Tag

object TagEx {

  val t = Tag.syntax("t")

  def veryGoodTagSet: Set[String] = starredTagSet(2)

  def goodTagSet: Set[String] = starredTagSet(1)
  
  def starredTagSet(s: Int): Set[String] = starredTagList(s).map(_.tagText).toSet
  
  def starredTagList(s: Int): List[Tag] = Tag.findAllBy(sqls.eq(t.tagKey, "import_mails").and.eq(t.starred, s).and.eq(t.deleted, 0))

}