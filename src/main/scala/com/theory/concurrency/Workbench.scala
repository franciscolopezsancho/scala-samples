package com.theory.concurrency

import org.slf4j.LoggerFactory
import scala.collection._
import scala.collection.immutable.Queue
import scala.concurrent.ExecutionContext
import scala.concurrent.Future
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicLong

object Workbench extends App {

  val atom = new MyAtomicInt(0)

  def execute(code: => Unit): Unit = {
    ExecutionContext.global.execute(
      new Runnable {
        def run() = code
      }
    )
  }

  for (i <- 1 to 32) {
    execute {
      val sleep = scala.util.Random.nextInt(2000)
      Thread.sleep(1000 + sleep)
      println(s"random sleep $sleep")
      println(atom.incrementAndGet())
    }
  }


  Thread.sleep(10000)
}

class MyAtomicInt(myInt: Int) {

  var int = new Integer(myInt)

  def incrementAndGet() = {
    int += 1
    int
  }
  

}