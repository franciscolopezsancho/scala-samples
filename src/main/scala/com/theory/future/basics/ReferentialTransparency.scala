package com.theory.future.basics

import java.util.concurrent.Executors

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Random

/**
 * Future is has not referential transparency
 * This means that the reference and referenced aren't equivalent.
 * Wouldn't be better call it referential equivalence?
 * In this case the right side means the creation and execution of a side effect
 * the left a pointer to it
 *
 * On scalaz Task or scalaIO Sync left and right are equal both point to an
 * asynchronous computation but don't execute it.
 *
 * see ANoParallelism
 * https://github.com/franciscolopezsancho/scala-samples/blob/master/src/main/scala/com/lightbend/future/problems/ANoParalelism.scala
 *
 * see BParallelism
 * https://github.com/franciscolopezsancho/scala-samples/blob/master/src/main/scala/com/lightbend/future/basics/Bparalelism.scala
 *
 */
object ReferentialTransparency {

  implicit val ec = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(2))


  val f1  = Future(println("hello"))


  for {
    a <- f1
    b <- f1
  } yield {
    println("done 1")
  }

  val rand =  Random

  for {
    a <- Future(println("aloha"))
    b <- Future(println("aloha"))
  } yield {
    println(a)
    println(b)
  }




  val fb  = Future(rand.nextInt())

  for {
    a <- fb
    b <- fb
  } yield {
    println(s"a: $a")
    println(s"a: $b")
  }





}
