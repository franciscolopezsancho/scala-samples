package com.theory.future.nested

import concurrent._
import java.util.concurrent.Executors

/**
  * When a funtion that outputs a Future acceptes  Future as input
  * def func1(doThis: => Int):Future
  */
object FutureFuture extends App {

  implicit val ec = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(2))

  val fF = Future(Future(5))

   fF.map(println)

  // val f = fF.flatMap {
  //   x => println(x)
  //     x
  // }

  // TODO listen

//  println(f)
//
//  fF.flatten.map(println)

}
