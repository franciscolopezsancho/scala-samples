package com.lightbend.future.problems

import java.util.concurrent.Executors

import scala.concurrent.{ExecutionContext, Future}


/**
 *
 * https://www.youtube.com/watch?v=USgfku1h7Hw&t=1038s
 *
 * Not only you have to pass the execution context everytime, and not pass the global one
 * but when refactoring how do you know which execution context should that be running?
 * let's say you have operations A,B,C in diferent classes and A and B are not very
 * demanding but C it is. You may share an ec with A,B and another with C but when refactoring
 * that's not type safe. Nothing in your compilation will help you on that?
 */
object ImplicitsImports {

}


/**
 * You may find yourself blocking for a period of time but after that
 * in case of timeout can you kill the thread you were waiting on? and
 * also is not BLOCKING? so how is it the same
 *
 * John DeGoes say it can't be backed into future
 *
 *
 * firstCompletedOf will not cancel the rest of the threads IF one fails. But if not failing will
 * cancel?
 *
 * Same in Future.sequence if one fails rest will keep going
 */
object AwaitAndKill {

}


/**
 *
 *
 * When for comprehension alias flatMapping  we submit to the threadpool
 * each future but nothing about the flatmap so we do not know in which step
 * it failed, just the first entrance.
 *
 *
 *
 */
object StackTrace {

}

/**
 * Future is not enough lazy. As Future(println("Hello, World")) can't be attached to a variable
 * see ANoParalelism
 * see ScalaIO https://www.youtube.com/watch?v=8_TWM2t97r4  min 6.16
 **/
object Lazyness extends App {

  implicit val ec = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(2))

  val fa = Future(println("Hello, World 1"))

  for {
    a <- fa
    b <- fa
  } yield {

  }


  for {
    a <- Future(println("Hello, World"))
    b <- Future(println("Hello, World"))
  } yield {

  }

}

//TODO proves it doesn't work
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
    else future.recoverWith { _ =>
      println(s"try number: $n")
      if (n == 0) Future(println("tired of trying"))
      else retrySideEffect(n - 1, future)
    }
  }

  retrySideEffect(5, printAndFail("Hello, World"))


  def printAndFail(toPrint: String): Future[Unit] = {
    Future {
      println(toPrint)
      throw new IllegalArgumentException()
    }
  }


}

//how can I use this to create a Monad that gives me empty in Int and
