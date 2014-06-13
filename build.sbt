name := "GoldRush Tasks"

organization := "jp.applicative"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.10.4"

libraryDependencies ++= Seq(
  "org.scalikejdbc" %% "scalikejdbc"        % "2.0.+",
  "mysql" % "mysql-connector-java" % "5.1.6",
  "ch.qos.logback"  %  "logback-classic"    % "1.1.+",
  "org.scalikejdbc" % "scalikejdbc-test_2.10" % "2.0.+" % "test",
  "com.twitter" %% "finagle-http" % "6.17.0" withSources(),
  "org.scalatest" % "scalatest_2.10" % "2.2.0" % "test" withSources() withJavadoc(),
  "org.scalacheck" %% "scalacheck" % "1.11.4" % "test" withSources() withJavadoc()
)

initialCommands := "import jp.applicative.gr.tasks._"

scalikejdbcSettings

