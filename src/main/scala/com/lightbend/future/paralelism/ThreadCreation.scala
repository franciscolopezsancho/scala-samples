package com.lightbend.future.paralelism

import java.util.Date


// This is deterministic
// TODO A thread doesn't run on top of another!! so on top of what do they run?
// of the cpu core,
// How can I make it non parallel
// see the
object ThreadsCreation extends App {
  Helper.print("starting")



  class MyThread extends Thread {
    override def run(): Unit = {
      Helper.print("starting")
      Helper.factorial(100000)
      Helper.print("finishing")
    }
  }

  val t1 = new MyThread
  t1.start()
  val t2 = new MyThread
  t2.start()
  // join makes the calling thread (main) to disactivate and let thread run till finishes.
  // Then it will rejoin the calling one give it's running power.

  t1.join()// notation is confusing should be join(t1) as is the main thread how listens
  t2.join()
  Helper.print("finishing")


  object Helper {
    def print(state: String): Unit = {
      println(s"$state at ${new Date().getSeconds}, thread ${Thread.currentThread.getName.toUpperCase}")
    }
    def factorial(n: Int) = (BigInt(1) to BigInt(n)).product
  }
}


