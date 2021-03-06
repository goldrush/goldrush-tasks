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
    val dbenv:String = opt.get("dbenv").flatMap(x => x).getOrElse("development")
    DBsWithEnv(dbenv).setupAll()
    
    val session = AutoSession

    args.toList match {
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
          case owners => owners.par.map {owner => f(Some(owner.id))(session)}
        }
    }
  }
  
  private def f(o: Option[Long])(implicit session: DBSession){
    val imm = new ImportMailMatching(o, session)
    val last_id = imm.matching
    val dmm = new DeliveryMailMatching(o, session)
    dmm.matching(last_id)
  }

}