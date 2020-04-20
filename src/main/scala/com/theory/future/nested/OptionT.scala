package com.theory.future.nested

import java.util.concurrent.Executors

import scala.concurrent.{ExecutionContext, Future}

/**
 * The problem con nested Monads is how to combine them in the right hierarchy
 * bare in mind than once a flatMap is called (as per for-comprehension) the F[_] must
 * remaing constant.
 *  see Monad in  
 *  @link  https://github.com/franciscolopezsancho/scala-samples/blob/master/src/main/scala/com/lightbend/future/basics/AFor.scala
 */
object OptionT extends App {

  implicit val ec = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(2))

  val task1: Future[Option[Int]] = Future.successful(Some(1))
  val task2: Future[Option[Int]] = Future.successful(Some(2))

  


  //the problem
  for {
    f1 <- task1
    f2 <- task2
    // if try now opt1 <- f1 will not match with original flatMap types (Future).
  } yield {
//      println(f1 + f2) ?  // required String found Option[Int]
  }


  // TODO is this an Semigroupal or Applicative?
  //the solution
  def combineOptions[A](a: Option[A], b: Option[A], f: (A,A) => A): Option[A] =  {
   for {
     opt1 <- a
     opt2 <- b
   } yield {
     (f(opt1, opt2))
   }
  }

  val res = for {
    f1 <- task1
    f2 <- task2
  } yield {
    combineOptions(f1,f2,(a: Int,b: Int) => a + b )
  }

  res.map(println)
}
