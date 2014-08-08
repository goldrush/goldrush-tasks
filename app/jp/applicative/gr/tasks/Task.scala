package jp.applicative.gr.tasks

import play.api._
import play.api.mvc._

import scalikejdbc._
import scalikejdbc.config._
import java.io.FileNotFoundException
import org.joda.time.DateTimeZone
import jp.applicative.gr.models.ex._

object Task {

  def mailMatching(dbenv: String, task: String): Unit = {

    GlobalSettings.loggingSQLAndTime = LoggingSQLAndTimeSettings(
      enabled = false,
      logLevel = 'DEBUG,
      warningEnabled = true,
      warningThresholdMillis = 1000L,
      warningLogLevel = 'WARN)

    java.util.TimeZone.setDefault(java.util.TimeZone.getTimeZone("UTC"))
    DBsWithEnv(dbenv).setupAll()

    implicit val session = AutoSession

    val imm = new ImportMailMatching
    List(task) match {
      case "pluralAll" :: _ =>
        Stream.from(0).foreach { i =>
          val list = ImportMailEx.findAllByPage(i)
          if (list.isEmpty) return
          list.foreach(im => imm.pluralAnalyze(im))
        }
      case _ =>
        val last_id = imm.matching
        val dmm = new DeliveryMailMatching
        dmm.matching(last_id)
    }
  }

}
