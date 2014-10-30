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

    GlobalSettings.loggingSQLAndTime = LoggingSQLAndTimeSettings(
      enabled = false,
      logLevel = 'DEBUG,
      warningEnabled = true,
      warningThresholdMillis = 1000L,
      warningLogLevel = 'WARN)

    java.util.TimeZone.setDefault(java.util.TimeZone.getTimeZone("UTC"))

    val imm = new ImportMailMatching(session)
    List(task) match {
      case "pluralAll" :: _ =>
        Stream.from(0).foreach { i =>
          val list = ImportMailEx.findAllByPage(i)(session)
          if (list.isEmpty) return
          list.foreach(im => imm.pluralAnalyze(im))
        }
      case _ =>
        OwnerEx.findAll()(session).par.map {owner =>
          log.debug(s"Matching owner_id: ${owner.id}")
          val last_id = imm.matching(owner.id)
          val dmm = new DeliveryMailMatching(session)
          dmm.matching(owner.id, last_id)
        }
    }
  }

}
