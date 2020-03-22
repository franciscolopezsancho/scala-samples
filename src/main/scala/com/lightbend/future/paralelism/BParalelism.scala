package com.lightbend.future.paralelism

import java.time.LocalDateTime
import java.util.concurrent.Executors

import scala.concurrent.{ExecutionContext, Future}

object BParalelism extends App {

  implicit val ec = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(10))

  println(LocalDateTime.now().getSecond)


  def task(durationMillis: Int) = {
    Thread.sleep(durationMillis)
    println(s"took me $durationMillis")
    durationMillis
  }

  val task1 = Future{task(3000)}
  val task2 = Future{task(4000)}


  val combinedF: Future[Int] =
    for {
      taskValue1 <- task1 // Takes 3 seconds to compute
      taskValue2 <- task2// Takes 4 seconds to compute
    } yield taskValue1 + taskValue2


  println(LocalDateTime.now.getSecond)


  combinedF.onComplete(println)

}
