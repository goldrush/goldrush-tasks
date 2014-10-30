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

    List(task) match {
      case "pluralAll" :: _ =>
        Stream.from(0).foreach { i =>
          val list = ImportMailEx.findAllByPage(i)(session)
          if (list.isEmpty) return
          val imm = new ImportMailMatching(0, session)
          list.foreach(im => imm.pluralAnalyze(im))
        }
      case _ =>
        OwnerEx.findAll()(session).par.map {owner => 
          log.debug("Process at owner_id: ${owner.id}")
          val imm = new ImportMailMatching(owner.id, session)
          val last_id = imm.matching
          val dmm = new DeliveryMailMatching(owner.id, session)
          dmm.matching(last_id)
        }
    }
  }

}
