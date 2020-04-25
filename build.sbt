import sbt._


name := "workbench"

version := "0.1"

scalaVersion := "2.13.1"
//TODO multiproject
libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-reflect" % "2.13.1",
  "com.typesafe.akka" %% "akka-actor-typed" % "2.6.1",
  "com.typesafe.slick" %% "slick" % "3.3.2",
  "com.h2database" % "h2" % "1.4.200",
  "org.typelevel" %% "cats-core" % "2.0.0",
  "org.scalacheck" %% "scalacheck" % "1.14.1",
  "commons-io" % "commons-io" % "2.6"
  
)

scalacOptions ++= Seq("-language:higherKinds")

initialCommands in console := """
  import com.theory.EntryPoint._
"""
  
