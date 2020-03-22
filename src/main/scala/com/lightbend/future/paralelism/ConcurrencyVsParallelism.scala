package com.lightbend.future.paralelism

import java.util.concurrent.Executors

import scala.concurrent.{ExecutionContext, Future}

/**
 * This program is concurrent one. Can
 */
object ConcurrencyVsParallelism extends App{




  implicit val ec = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(2))



    def task(name: String, rest: Int ):Int = {
      val beginning = System.currentTimeMillis()
      println(s"started task $name")
      Thread.sleep(rest)
      val timeSpent = (System.currentTimeMillis() - beginning).toInt/1000
      println(s"finished $name in $timeSpent")
      timeSpent
    }


  val beginning = System.currentTimeMillis()

  val task1 = Future(task("task1", 3000))
  val task2 = Future(task("task2", 2000))

  for {
    a <- task1
    b <- task2
  } yield {
    println(s"total execution time ${a + b}")
    val timeSpent = (System.currentTimeMillis() - beginning).toInt/1000
    println(s"total duration: $timeSpent ")


  }

  println("I'm not waiting for guys above")



}
