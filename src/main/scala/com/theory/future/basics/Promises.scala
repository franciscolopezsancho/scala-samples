package com.theory.future.basics

import java.util.concurrent.Executors

import scala.concurrent.{ExecutionContext, Future, Promise}
import scala.util.{Success, Try}

import scala.concurrent.blocking

/**
 * instead of directing creating Future as a read-only structure
 * we may create it through a `Promise` such as the Future will complete
 * depending on when the Promise does.
 *
 *
 * Promise future is lazy evaluated
 *
 * TODO but the question now is how do I create
 * i.e a query to db that gives back a promise.
 *
 *
 *
 * Let's see an example
 */
object Promises extends App {

  implicit val ec = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(3))

  val p1 = Promise[String]()
  p1.future.foreach( x => println(s"finally done $x"))





  println("waiting till the promise gets real!!")
  //recommended to be explicit about it
  blocking{ Thread.sleep(3000) }



  //TODO create callback API
      // maybe UI swing kind of thing
  p1.complete(Success("doing my job"))


}
object Promises2 extends App {

  implicit val ec = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(3))

  def factorial(n: Int) = (BigInt(1) to BigInt(n)).product


  val p1 = Promise[Int]()

  // This is saying when promise is completed run a future taking the value from
  // the promise p1 and passing it to factorial  
  p1.future.map(factorial)


  println("still lazy")
  blocking {Thread.sleep(3000)}


  p1.complete(Success(3000))

}

