package com.theory.future.paralelism

import java.time.LocalDateTime
import java.util.concurrent.Executors

import scala.concurrent.{ExecutionContext, Future}
/**
  * This show a way to build the Futures inside a for comprehension an preserve parallelism
  */
object ElegantSolution extends App {

  implicit val ec = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(1))

  println(s" seconds of this minute ${LocalDateTime.now().getSecond}")

  def factorial(n: Int) = (BigInt(1) to BigInt(n)).product


  def task(durationMillis: Int) = {
    factorial(durationMillis)
    println(s"took me $durationMillis")
    durationMillis
  }



  val combinedF: Future[Int] =
    for {
      _ <- Future.unit
      task1 = Future{task(3000)}
      task2 = Future{task(4000)}
      taskValue1 <- task1// Takes 3 seconds to compute
      taskValue2 <- task2// Takes 4 seconds to compute
    } yield taskValue1 + taskValue2

    //TODO explain how the compiler tracks that. Doesn't show in desugared :(
//  val combinedX: Future[Int] =
//    for {
//      _ <- List.empty
//      task1 = Future{task(3000)}
//      task2 = Future{task(4000)}
//      taskValue1 <- task1// Takes 3 seconds to compute
//      taskValue2 <- task2// Takes 4 seconds to compute
//    } yield taskValue1 + taskValue2




  combinedF.onComplete{ _ =>
    println(s" seconds of this minute ${LocalDateTime.now().getSecond}")
    System.exit(0)
  }

}
