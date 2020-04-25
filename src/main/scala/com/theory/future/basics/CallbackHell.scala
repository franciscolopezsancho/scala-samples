package com.theory.future.basics

import java.util.concurrent.Executors
import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}


/**
 * Futures aren't such. Don't happen in the future but now.
 * They are async though at least to the running thread.
 * What they have about `future` is the answer(callback) not the call itself
 *

 *
 * Callback can be hell though
 */
object CallbackHell extends App {

  implicit val ec = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(2))
  
  def divideAnAlter(num: BigInt, denom: BigInt,alter: BigInt => BigInt): Future[BigInt] =
    Future(alter(num / denom))

  def nonBlockingTask(millis: Long): Unit = {
    val now = System.currentTimeMillis()
    println(s"### ${Thread.currentThread.getName} WORKING...")
    while(now + millis < System.currentTimeMillis()){}
    println(s"### ${Thread.currentThread.getName} FINISHED TASK")

  }

  def asyncFactorial(n: Int): Future[BigInt] = Future((BigInt(1) to BigInt(n)).product)

  val factorialInput1 = 220
  val factorialInput2 = 200

  asyncFactorial(factorialInput1).onComplete {
    case Success(factorial1) =>
      asyncFactorial(factorialInput2).onComplete {
        case Success(factorial2) => divideAnAlter(factorial1, factorial2,(b: BigInt) => b * 2)
          .onComplete {
            case Success(value) => print(value)
            case Failure(exception) => throw exception
          }
        case Failure(exception) => println(exception)
      }
    case Failure(exception) => println(exception)
  }


  /**
   * 
  */
  val r = for { // flatMaps
    f1 <- asyncFactorial(factorialInput1)
    f2 <- asyncFactorial(factorialInput2)
    result <- divideAnAlter(f1, f2, (b: BigInt) => b * 2)
   } yield { // maps
    println(result.toString.substring(0,10))
    System.exit(0)
  }


  /**
   * But there's something wrong in this for. Is not parallel
   * NoParallelism
   * https://github.com/franciscolopezsancho/scala-samples/blob/master/src/main/scala/com/lightbend/future/paralelism/NoParallelism.scala
   */
}
