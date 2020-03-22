import sbt._


name := "workbench"

version := "0.1"

scalaVersion := "2.13.1"

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-reflect" % "2.13.1",
  "com.typesafe.akka" %% "akka-actor-typed" % "2.6.1"
  )

initialCommands in console := """
  import com.lightbend.selftypes._
  import com.lightbend.selftypes.pathdependenttypes._
  import com.lightbend.`implicit`._
"""
  
