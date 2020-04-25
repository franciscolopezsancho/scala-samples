package com.theory.future.basics

import java.util.concurrent.Executors

import scala.concurrent.{ExecutionContext, Future}


/**
 * Future will run the operation in another thread, but EAGER!
 * One way of looking at a scala.concurrent.Future is that it’s an “Eventually[Try[_]]”, in other
 */
object AFors extends App {


  //no tail recursive
  def factorial(n: Int) = (BigInt(1) to BigInt(n)).product


  implicit val ec = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(10))

  val f = Future {
      factorial(200000)
  }

  println("while factorial is running I'm doing somehing else. Namely, printing this")


  // map has an underlying `transform` that will wrap the `map` function A => B in Try[A] => Try[B] @see top
  // IF Success
  // so is equivalent to call onComplete Success
  // TODO why there's an implementation and one abstract?
  // have a look at https://viktorklang.com/blog/Futures-in-Scala-2.12-part-3.html
  f.map(v => println(s"map ${v.toString.size}"))
    // Futures is a Functor as can sequence computations fulfilling identity and composition (TODO associativity?)
    //                                                                                  functor.map(f).map(g) = functor.map(g(f(_))) // have a look at the order
    //   trait Functor[F[_]] = {
    //     def map[A,B](value: F[A])(f: A => B): F[B]
    //   }



  // flatMap is a map tha creates another Monad (same type) and flatten both Monads.
  // map can take you out of the monad. Not working with pure function any more
  // this is done with the underlying `transformWith` that will wrap the `map` function A => B in Try[A] => Future[B]
  // IF Success
  f.flatMap(v => Future(println(s"flatMap ${v.toString.size}")))
    //     Future is a Monad as can sequence computations with left and right identity and associativity (TODO how is differrent from composition)
    //                                                                                   monad.flatMap(f).flatMap(g) = monad.flatMap(x => f(x).flatMap(g))
    //  trait Monad[F[_]] = {
    //    (def pure[A](value: A): F[A]) //TODO not to explain here
    //    def flatMap[A,B](value: F[A](f: A => F[B]): F[B]
    //  }
    // reference: scala-with-cats
    // have a look at http://www.arolla.fr/blog/wp-content/uploads/2018/10/DomainModelingwithMonoids.pdf




  // is a map with a filter. See above.
  f.filter(_ > BigInt(Long.MaxValue)).onComplete(v => println(s"filter, it was bigger "))

  // see callback inside (onComplete)
  // see Callback.scala next exercise
  f.foreach(v => println(s"foreach ${v.toString.substring(0,100)}"))


  // notice how the order is undeterministic


  /**
    * Callback.scala
    * https://github.com/franciscolopezsancho/scala-samples/blob/master/src/main/scala/com/lightbend/future/basics/Callback.scala
  */
  /**
    * ReferentialTransparency.scala
    * https://github.com/franciscolopezsancho/scala-samples/blob/master/src/main/scala/com/lightbend/future/basics/ReferentialTransparency.scala
  */
}
