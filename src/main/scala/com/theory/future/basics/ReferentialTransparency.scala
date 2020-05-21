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
 *  After this example is more obvious why ANoParallelism
 *
 * see ANoParallelism
 * https://github.com/franciscolopezsancho/scala-samples/blob/master/src/main/scala/com/lightbend/future/problems/ANoParalelism.scala
 *
 * see BParallelism
 * https://github.com/franciscolopezsancho/scala-samples/blob/master/src/main/scala/com/lightbend/future/basics/Bparalelism.scala
 *
 */
object ReferentialTransparency extends App {


  implicit val ec = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(2))

  val rand = Random

  val x = Future(Random.nextInt)
 
  println((x, x))

  val (y,z)= (Future(Random.nextInt), Future(Random.nextInt))

  Thread.sleep(100) // for the sake to show z completed
  println((y, z))


  //(x,x) are equal
  //(y,z) are not
  // which proves the point that the reference is not the same that what's referenced
  // there's not transparency in there. 
}






