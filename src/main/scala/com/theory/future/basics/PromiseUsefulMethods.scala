package com.theory.future.basics

import java.util.concurrent.Executors

import scala.concurrent.{ExecutionContext, Future, Promise}
import scala.util.{Success, Try}

import scala.concurrent.blocking
import scala.util.control.NonFatal
import org.apache.commons.io.monitor.FileAlterationMonitor
import org.apache.commons.io.monitor.FileAlterationObserver
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor
import java.io.File
import cats.instances.list
import java.util.concurrent.CancellationException
import scala.util.Failure
import scala.util.Random

/**
  * instead of creating a Future as a read-only structure
  * we may create it through a `Promise` as a write-only structure
  *    over its Future (one to one relation) such as it will complete when Promise gets written.
  *
  * As the dual of the Future it also holds a Try[T], value we can fulfill with a value or an exception
  *
  * Promise future is evaluated on demand, not eager as a Future
  *
  * Let's see an example
  */


object TryComplete extends App {

  implicit val ec =
    ExecutionContext.fromExecutor(Executors.newFixedThreadPool(3))

  val p1 = Promise[String]()
  p1.future.foreach(x => println(s"finally $x"))

  println("waiting till the promise gets real!!")
  //recommended to be explicit about it
  blocking { Thread.sleep(2000) }

  p1.complete(Success("doing my job"))
  //it will try so if something goes wrong will not throw the Exception
  assert(p1.tryComplete(Success("doing my job")) == false)

  // not only we get true but the app keeps runnig


}

object TrySuccess extends App {

  implicit val ec =
    ExecutionContext.fromExecutor(Executors.newFixedThreadPool(3))

  val p1 = Promise[String]()
  p1.future.foreach(x => println(s"finally $x"))

  println("waiting till the promise gets real!!")
  //recommended to be explicit about it
  blocking { Thread.sleep(100) }

  //just easier then tryComplete
  assert(p1.trySuccess("doing my job") == true)



}

object TryFailure extends App {

  implicit val ec =
    ExecutionContext.fromExecutor(Executors.newFixedThreadPool(3))

  val p = Promise[String]()
  p.tryFailure(new RuntimeException("br0ken"))
 
  //notice how the app stops!! but the exception doesn't pop up this thread :|



}


object CompleteWith extends App {

  implicit val ec =
    ExecutionContext.fromExecutor(Executors.newFixedThreadPool(3))

  val p1 = Promise[String]()
  p1.future.foreach(x => println(s"finally $x"))

  println("waiting till the promise gets real!!")
  //recommended to be explicit about it
  blocking { Thread.sleep(1000) }

  p1.completeWith(Future("doing my job"))


  //notice how flattens the Future in 92



}