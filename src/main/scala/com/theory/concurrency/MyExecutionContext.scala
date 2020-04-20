package com.theory.concurrency

import java.util.concurrent.Executor
import scala.concurrent.ExecutionContext

object MyExecutionContext extends App {

  def execute(block: => Unit) = ExecutionContext.global.execute(
    new Runnable { def run() = block }
  )

  for (i <- 1 to 32) {
    execute {
      Thread.sleep(2000)
      println(
        s"$i task finished at ${System.currentTimeMillis() / (1000 * 60 * 60 * 24 * 7)}"
      )
    }
  }
  Thread.sleep(10000)
  println("I'm done")
  
  

}
