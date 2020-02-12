import sbt._


name := "workbench"

version := "0.1"

scalaVersion := "2.13.1"

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-reflect" % "2.13.1"
  )

initialCommands in console := """
  import Bench._
"""
  
