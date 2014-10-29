import com.typesafe.sbt.SbtNativePackager._
import NativePackagerKeys._

name := "GoldRush Tasks"

organization := "jp.applicative"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.2"

val scalikeJDBCVersion = "2.1.2"

packageArchetype.java_application

mappings in Universal <+= (packageBin in Compile, sourceDirectory ) map { (_, src) =>
    // we are using the reference.conf as default application.conf
    // the user can override settings here
    val conf = src / "main" / "resources" / "application.conf"
    conf -> "conf/application.conf"
}

bashScriptConfigLocation := Some("${app_home}/../conf/jvmopts")

libraryDependencies ++= Seq(
  "org.scalikejdbc" %% "scalikejdbc"             % scalikeJDBCVersion withSources() withJavadoc(),
  "org.scalikejdbc" %% "scalikejdbc-config"      % scalikeJDBCVersion,
  "org.scalikejdbc" %% "scalikejdbc-play-plugin" % "2.3.2",
  "mysql" % "mysql-connector-java" % "5.1.6",
  "ch.qos.logback"  %  "logback-classic"    % "1.1.+",
  "org.scalikejdbc" %% "scalikejdbc-test" % scalikeJDBCVersion % "test" withSources() withJavadoc(),
  //"com.twitter" %% "finagle-http" % "6.17.0" withSources(),
  "org.scalatest" %% "scalatest" % "2.2.0" % "test" withSources() withJavadoc(),
  "org.scalacheck" %% "scalacheck" % "1.11.4" % "test" withSources() withJavadoc()
)

initialCommands := "import jp.applicative.gr.tasks._"

scalikejdbcSettings

lazy val root = (project in file(".")).enablePlugins(PlayScala)

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws
)

