package jp.applicative.gr.models.ex

import scalikejdbc._
import org.joda.time.{DateTime}
import jp.applicative.gr.models.Tag

object TagEx {

  val t = Tag.syntax("t")

  def veryGoodTagSet(owner_id: Long)(implicit session: DBSession): Set[String] = starredTagSet(owner_id)(2)

  def goodTagSet(owner_id: Long)(implicit session: DBSession): Set[String] = starredTagSet(owner_id)(1)
  
  def starredTagSet(owner_id: Long)(s: Int)(implicit session: DBSession): Set[String] = starredTagList(owner_id)(s).map(_.tagText).toSet
  
  def starredTagList(owner_id: Long)(s: Int)(implicit session: DBSession): List[Tag] = Tag.findAllBy(sqls.eq(t.ownerId, owner_id).and.eq(t.tagKey, "import_mails").and.eq(t.starred, s).and.eq(t.deleted, 0))

}