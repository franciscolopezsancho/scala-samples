package com.theory.future.failure

import java.util.concurrent.Executors

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}
import scala.util.Random


/**
 * When failure we may decide do nothing about it and ignore it
 * or deal with it maybe throwing an Exception. Not recommended.
 */
object FutureFailure extends App {

  implicit val ec = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(10)) 
  //do nothing about it, will ignore it. As get's a Try[Failure] and will short circuit the map
  def silentVersionDivideAndAlter(num: BigInt, denom: BigInt, f: BigInt => Unit) =
    Future(num / denom).map(f)

  //handling it like this we now can handle the failure in an specific way
  def noisyVersionDivideAndAlter(num: BigInt, denom: BigInt, f: BigInt => Unit) =
    Future(num / denom).onComplete{
      case Success(value) => f(value)
      case Failure(ex) => throw ex // have a go with Future[Either[Exception,Unit]]
    }

    

  //handling it like this we can recover in case ...
  // if not the case we are back to above @silentVersionDivideAndAlter
  def recoverFromZero(num: BigInt, denom: BigInt, f: BigInt => Unit) =
    Future(num / denom).
      recover { case ae: ArithmeticException => BigInt(0) }
      .map(f)



   //handling it like this we can recover with a PartialFunction[Throwable, Future[T]] 
   // and execute another Future.
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
  

  // try one at a time 


  silentVersionDivideAndAlter(1, 0, println)

  // noisyVersionDivideAndAlter(1, 0, println)

  // recoverFromZero(1, 0, println)

  // retrySideEffect(5, printAndFail("testing spawning alternative Futures"))



  Thread.sleep(100)
  System.exit(0)


}
