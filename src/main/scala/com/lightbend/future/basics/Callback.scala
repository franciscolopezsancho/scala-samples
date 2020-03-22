package com.lightbend.future.basics

import java.util.concurrent.Executors

import com.lightbend.future.paralelism.Breakfast.Helper

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}


/**
 * After
 */
object Callback extends App {

  //Don't let it crash

  implicit val ec = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(10))

  def divide(num: BigInt, denom: BigInt, f: BigInt => Unit) =
    Future(num / denom).onComplete {
      case Success(value) => f(value)
      case Failure(exception) => exception
    }

  def divideWithZeros(num: BigInt, denom: BigInt, f: BigInt => Unit) =
    Future(num / denom).
      recover { case ae: ArithmeticException => BigInt(0) }
      .onComplete {
        case Success(value) => f(value)
        case Failure(exception) => exception
      }


  divide(1, 0, println)
  divideWithZeros(1, 0, println)
}

object Callback2 extends App {
  // let's try to create factorial two numbers and divide them

  implicit val ec = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(10))

  def divideAnAlter(num: BigInt, denom: BigInt,f: BigInt => BigInt): Future[BigInt] =
    Future(f(num / denom))


  Helper.futFactorial(100).onComplete {
    case Success(factorial1) =>
      Helper.futFactorial(2000).onComplete {
        case Success(factorial2) => divideAnAlter(factorial1, factorial2,(b: BigInt) => b * 2)
          .recover { case ae: ArithmeticException => 0 }
          .onComplete {
            case Success(value) => print(value)
            case Failure(exception) => exception
          }
        case Failure(exception) => println(exception)
      }
    case Failure(exception) => println(exception)
  }



  val r = for {
    f1 <- Helper.futFactorial(100)
    f2 <- Helper.futFactorial(200)
    restult <- divideAnAlter(f1, f2, (b: BigInt) => b * 2)
   } yield (f1,f2,restult)

  r.map(println)

}
