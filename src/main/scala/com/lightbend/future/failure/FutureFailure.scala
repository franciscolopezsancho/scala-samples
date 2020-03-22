package com.lightbend.future.failure

import java.util.concurrent.Executors

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}


/**
 *
 */
object FutureFailure extends App {

  //Don't let it crash

  implicit val ec = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(10))

  def divide(num: Int, denom: Int, f: Int => Unit) =
    Future{num / denom}.onComplete {
      case Success(value) => f(value)
      case Failure(exception) => println(exception)
    }


  divide(1, 0,println) // but what about the exception? :)

  def divedeWithZeros(num: Int, denom: Int, f: Int => Unit) =
    Future{num / denom}.
      recover { case ae: ArithmeticException => 0 }
      .onComplete {
        case Success(value) => f(value)
        case Failure(exception) => println(exception)
      }



  divedeWithZeros(1,0 ,println)
}
