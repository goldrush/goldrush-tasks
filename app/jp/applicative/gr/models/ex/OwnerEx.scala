package jp.applicative.gr.models.ex

import scalikejdbc._
import org.joda.time.{DateTime, DateTimeZone}
import jp.applicative.gr.models.Owner

object OwnerEx {
  val o = Owner.syntax("o")

  def findAll()(implicit session: DBSession) = Owner.findAllBy(sqls.eq(o.deleted, 0).orderBy(o.id))

}