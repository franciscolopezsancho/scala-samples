package com.theory.future.problems

import java.util.concurrent.Executors

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Random, Success}


/**
  * Here is a sample of how to create a lazy future 
  * I don't think this is Lazy. Just a val would be able to prove me that.
  * Or maybe it is lazy. The problem is that using val it can't ever be lazy
  * 
  */
object AbstractinVsLazines2 extends App {

  implicit val ec = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(2))


    def retry(n: Int = 0, future: Future[Int]): Future[Int] = {
      if (n < 0) future
      else future.recoverWith { _ =>
        println(s"try number: $n")
        if (n == 0) Future(0)
        else retry(n - 1, future)
      }
    }

    // retry(5, Future(1 / 0)).onComplete(println)


  def retrySideEffect(n: Int = 0, f:  => Unit): Future[Unit] = {
    if (n < 0) Future(f)
    else Future(f).recoverWith { case i:IllegalArgumentException =>
      println(s"try number: $n")
      if (n == 0) Future(println("tired of trying"))
      else retrySideEffect(n - 1, f)
    }
  }




  def printAndFail(toPrint: String): Unit = {
    println(s"$toPrint | ${Random.nextInt}")
    throw new IllegalArgumentException()
  }
  
  //This proves it's lazy 
  // TODO not sure this is proved actual
  // let's see if we can prove
  retrySideEffect(5, printAndFail("dddddd"))

// you could use a list and then traverse?
  val f = Future(Option(Future(Option(1))))





}



object KeepRunning extends App {
  // TODO how to stop running futures?
  // let's say you just want the first result
      //i.e. requesting redundant info from different system you don't know on front is fastest
  // let's in case one fails you want the rest to cancel
      //i.e. composing an object from multiple async requests
  
   //cancel your futures https://viktorklang.com/blog/Futures-in-Scala-protips-6.html 
   //another solutions: @link https://typelevel.org/cats-effect/datatypes/io.html#cancelable-processes

}

//TODO can I sent a Future to an Actor? a remote Actor
object Serialization {
  
}

object Performance {
  //each map on a future will create a Future that will have to be scheduled. This means
  // each time one of this future is loaded cached is flushed. Not optimal
  // 
  // recommended use is transform and all maps inside
  // https://viktorklang.com/blog/Futures-in-Scala-protips-5.html
}
