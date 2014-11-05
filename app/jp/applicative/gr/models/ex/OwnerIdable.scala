package jp.applicative.gr.models.ex

import scalikejdbc._

trait OwnerIdable[A] {
  val q: QuerySQLSyntaxProvider[SQLSyntaxSupport[A], A]
  val owner_id: Option[Long]
  
  lazy val _sqls:SQLSyntax = owner_id match {
    case Some(o) => sqls.eq(q.ownerId, owner_id)
    case _ => sqls.isNull(q.ownerId)
  }
  

}