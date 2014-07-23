package jp.applicative.gr.tasks

import scalikejdbc._
import scalikejdbc.config._
import java.io.FileNotFoundException
import org.joda.time.DateTimeZone
import jp.applicative.gr.models.ex._

object Main {
  
  def analyzeArgs(args: List[String]): Map[String, Option[String]] = {
    args match {
      case "-d" :: x :: xs => Map("dbenv" -> Some(x)) ++ analyzeArgs(xs)
      case "-t" :: x :: xs => Map("task" -> Some(x)) ++ analyzeArgs(xs)
      case _ => Map()
    }
  }

  def main(args: Array[String]): Unit = {
    val opt = analyzeArgs(args.toList)
    GlobalSettings.loggingSQLAndTime = LoggingSQLAndTimeSettings(
      enabled = false,
      logLevel = 'DEBUG,
      warningEnabled = true,
      warningThresholdMillis = 1000L,
      warningLogLevel = 'WARN)

    java.util.TimeZone.setDefault(java.util.TimeZone.getTimeZone("UTC"))
    val dbenv:String = opt.get("dbenv").get.getOrElse("development")
    DBsWithEnv(dbenv).setupAll()
    
    implicit val session = AutoSession

    val imm = new ImportMailMatching
    args.toList match {
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