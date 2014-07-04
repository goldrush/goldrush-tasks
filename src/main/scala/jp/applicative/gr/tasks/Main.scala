package jp.applicative.gr.tasks

import scalikejdbc._
import java.io.FileNotFoundException
import org.joda.time.DateTimeZone
import jp.applicative.gr.models.ex._

object Main {

  case class JDBCSettings(driver: String, url: String, username: String, password: String, schema: String)

  case class GeneratorSettings(packageName: String, template: String, testTemplate: String, lineBreak: String, caseClassOnly: Boolean, encoding: String)

  def loadSettings(): (JDBCSettings, GeneratorSettings) = {
    val props = new java.util.Properties
    try {
      using(new java.io.FileInputStream("project/scalikejdbc-mapper-generator.properties")) {
        inputStream => props.load(inputStream)
      }
    } catch {
      case e: FileNotFoundException =>
    }
    if (props.isEmpty) {
      using(new java.io.FileInputStream("project/scalikejdbc.properties")) {
        inputStream => props.load(inputStream)
      }
    }
    (JDBCSettings(
      driver = Option(props.get("jdbc.driver")).map(_.toString).getOrElse(throw new IllegalStateException("Add jdbc.driver to project/scalikejdbc-mapper-generator.properties")),
      url = Option(props.get("jdbc.url")).map(_.toString).getOrElse(throw new IllegalStateException("Add jdbc.url to project/scalikejdbc-mapper-generator.properties")),
      username = Option(props.get("jdbc.username")).map(_.toString).getOrElse(""),
      password = Option(props.get("jdbc.password")).map(_.toString).getOrElse(""),
      schema = Option(props.get("jdbc.schema")).map(_.toString).orNull[String]), GeneratorSettings(
        packageName = Option(props.get("generator.packageName")).map(_.toString).getOrElse("models"),
        template = Option(props.get("generator.template")).map(_.toString).getOrElse("executableSQL"),
        testTemplate = Option(props.get("generator.testTemplate")).map(_.toString).getOrElse("specs2unit"),
        lineBreak = Option(props.get("generator.lineBreak")).map(_.toString).getOrElse("LF"),
        caseClassOnly = Option(props.get("generator.caseClassOnly")).map(_.toString.toBoolean).getOrElse(false),
        encoding = Option(props.get("generator.encoding")).map(_.toString).getOrElse("UTF-8")))
  }

  def main(args: Array[String]): Unit = {
    GlobalSettings.loggingSQLAndTime = LoggingSQLAndTimeSettings(
      enabled = false,
      logLevel = 'DEBUG,
      warningEnabled = true,
      warningThresholdMillis = 1000L,
      warningLogLevel = 'WARN)

    java.util.TimeZone.setDefault(java.util.TimeZone.getTimeZone("UTC"))
    val (j, d) = loadSettings()
    Class.forName(j.driver)
    ConnectionPool.singleton(j.url, j.username, j.password)
    val session = AutoSession

    val m = new DeliveryMailMatching

    args.toList match {
      case "pluralAll" :: _ => 
	    Stream.from(0).foreach { i => 
	      val list = ImportMailEx.findAllByPage(i)
	      if(list.isEmpty) return
	      list.foreach(im => m.pluralAnalyze(im))
	    }
      case _ =>
        m.matching
    }
  }

}