package com.lightbend.future.paralelism

import java.util.concurrent.{CountDownLatch, Executors, TimeUnit}

import com.lightbend.future.paralelism.Breakfast.Helper

import scala.concurrent.{ExecutionContext, Future}


/**
 * How to obtain parallelism and waiting to complete.
 * Same as onComplete? not really right? This is blocking
 *
 */
object WithoutFuture extends App{


  val work: List[Int => BigInt] = List(Helper.factorial,Helper.factorial)

  val latch: CountDownLatch = new CountDownLatch(work.length)

  work.map { worker =>
    new Thread {
      override def run = {
        try
          Helper.print(s"result ${worker(100000).toString.substring(0,10)}...")
        finally latch.countDown()
      }
    }.start()
  }

  println("I'm not waiting")
  //to wait or not to wait, that is the question

  latch.await(10,TimeUnit.SECONDS)
  println("I rather wait")

}

object WithoutFuture2 extends App {

  implicit val ec = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(2))

  val work: List[Int => BigInt] = List(Helper.factorial,Helper.factorial)

  val futures = work.map(worker => Future(worker(100000)))

  println("I'm not waiting")

  Future.sequence(futures).onComplete{ done =>
    Future(Helper.print(s"result ${done.toString.substring(0,10)}..."))
    println("I rather wait")
  }





}
