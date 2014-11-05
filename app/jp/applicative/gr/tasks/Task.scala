package jp.applicative.gr.tasks

import scalikejdbc._
import scalikejdbc.config._
import java.io.FileNotFoundException
import org.joda.time.DateTimeZone
import jp.applicative.gr.models.ex._
import org.slf4j.LoggerFactory

object Task {

  private val log = LoggerFactory.getLogger(this.getClass())

  def mailMatching(task: String): Unit = {
    val session = AutoSession

    java.util.TimeZone.setDefault(java.util.TimeZone.getTimeZone("UTC"))

    List(task) match {
      case "pluralAll" :: _ =>
        Stream.from(0).foreach { i =>
          val importMail = new ImportMailEx(None)
          val list = importMail.findAllByPage(i)(session)
          if (list.isEmpty) return
          val imm = new ImportMailMatching(None, session)
          list.foreach(im => imm.pluralAnalyze(im))
        }
      case _ =>
        OwnerEx.findAll()(session) match {
          case Nil => f(None)(session)
          case owners => owners.par.map { owner => f(Some(owner.id))(session) }
        }
    }
  }

  private def f(o: Option[Long])(implicit session: DBSession) {
    val imm = new ImportMailMatching(o, session)
    val last_id = imm.matching
    val dmm = new DeliveryMailMatching(o, session)
    dmm.matching(last_id)
  }

  def deleteMailMatching {
    val session = AutoSession
    val importMail = new ImportMailEx(None)
    importMail.deleteImportMailMatches(14)(session)
    log.debug("deleteImportMailMatches done")

    val deliveryMail = new DeliveryMailEx(None)
    deliveryMail.deleteDeliveryMailMatches(14)(session)
    log.debug("deleteDeliveryMailMatches done")
  }

}
