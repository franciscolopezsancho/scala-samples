package com.theory.future.paralelism

import java.time.LocalDateTime
import java.util.concurrent.Executors

import scala.concurrent.{ExecutionContext, Future}

object BParallelism extends App {

  implicit val ec = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(1))

  println(s" seconds of this minute ${LocalDateTime.now().getSecond}")


  def task(durationMillis: Int) = {
    Thread.sleep(durationMillis)
    println(s"took me $durationMillis")
    durationMillis
  }

  val task1 = Future{task(3000)}
  val task2 = Future{task(4000)}


  // please desugra with IntelliJ. Right button on it
  val combinedF: Future[Int] =
    for {
      taskValue1 <- task1// Takes 3 seconds to compute
      taskValue2 <- task2// Takes 4 seconds to compute
    } yield taskValue1 + taskValue2 





  combinedF.onComplete{ _ =>
    println(s" seconds of this minute ${LocalDateTime.now().getSecond}")
    System.exit(0)
  }

  //not parallel? missing something?

}
