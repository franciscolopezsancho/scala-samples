package com.lightbend.future.problems

import java.util.concurrent.Executors

import scala.concurrent.{ExecutionContext, Future}

object AbstractinVsLaziness extends App {

  implicit val ec = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(2))


  //  def retry(n: Int = 0, future: Future[Int]): Future[Int] = {
  //    if (n < 0) future
  //    else future.recoverWith { _ =>
  //      println(s"try number: $n")
  //      if (n == 0) Future(0)
  //      else retry(n - 1, future)
  //    }
  //  }
  //
  //  retry(5, Future(1 / 0)).onComplete(println)


  def retrySideEffect(n: Int = 0, future: Future[Unit]): Future[Unit] = {
    if (n < 0) future
    else future.recoverWith { case i:IllegalArgumentException =>
      println(s"try number: $n")
      if (n == 0) Future(println("tired of trying"))
      else retrySideEffect(n - 1, future)
    }
  }

  def printAndFail(toPrint: String): Future[Unit] = {
    Future {
      println(toPrint)
      throw new IllegalArgumentException()
    }
  }

  retrySideEffect(5, printAndFail("Hello, World"))





}

object ClosingConnection extends App {

}
