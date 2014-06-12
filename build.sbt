name := "GoldRush Tasks"

organization := "jp.applicative"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  "org.scalatest" % "scalatest_2.10" % "2.2.0" % "test" withSources() withJavadoc(),
  "org.scalacheck" %% "scalacheck" % "1.11.4" % "test" withSources() withJavadoc()
)

initialCommands := "import jp.applicative.goldrushtasks._"

