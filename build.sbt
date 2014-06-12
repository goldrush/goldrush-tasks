name := "GoldRush Tasks"

organization := "jp.applicative"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.10.4"

libraryDependencies ++= Seq(
  "com.twitter" %% "finagle-http" % "6.17.0",
  "org.scalatest" % "scalatest_2.10" % "2.2.0" % "test" withSources() withJavadoc(),
  "org.scalacheck" %% "scalacheck" % "1.11.4" % "test" withSources() withJavadoc()
)

initialCommands := "import jp.applicative.goldrushtasks._"

