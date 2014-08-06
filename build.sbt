import com.typesafe.sbt.SbtNativePackager._
import NativePackagerKeys._

name := "GoldRush Tasks"

organization := "jp.applicative"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.10.4"

val scalikeJDBCVersion = "2.0.6"

packageArchetype.java_application

mappings in Universal <+= (packageBin in Compile, sourceDirectory ) map { (_, src) =>
    // we are using the reference.conf as default application.conf
    // the user can override settings here
    val conf = src / "main" / "resources" / "application.conf"
    conf -> "conf/application.conf"
}

bashScriptConfigLocation := Some("${app_home}/../conf/jvmopts")

libraryDependencies ++= Seq(
  "org.scalikejdbc" %% "scalikejdbc"        % scalikeJDBCVersion withSources() withJavadoc(),
  "org.scalikejdbc" %% "scalikejdbc-config"  % scalikeJDBCVersion,
  "mysql" % "mysql-connector-java" % "5.1.6",
  "ch.qos.logback"  %  "logback-classic"    % "1.1.+",
  "org.scalikejdbc" % "scalikejdbc-test_2.10" % scalikeJDBCVersion % "test" withSources() withJavadoc(),
  //"com.twitter" %% "finagle-http" % "6.17.0" withSources(),
  "org.scalatest" % "scalatest_2.10" % "2.2.0" % "test" withSources() withJavadoc(),
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

