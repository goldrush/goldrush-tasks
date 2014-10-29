package jp.applicative.gr.tasks

import scalikejdbc._
import scalikejdbc.config._
import java.io.FileNotFoundException
import org.joda.time.DateTimeZone
import jp.applicative.gr.models.ex._

object Task {

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
        val last_id = imm.matching
        val dmm = new DeliveryMailMatching(session)
        dmm.matching(last_id)
    }
  }

}
