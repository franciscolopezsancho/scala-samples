package com.theory.future.basics

import java.util.concurrent.Executors

import scala.concurrent.{ExecutionContext, Future, Promise}
import scala.util.{Success, Try}

import scala.concurrent.blocking
import scala.util.control.NonFatal
import org.apache.commons.io.monitor.FileAlterationMonitor
import org.apache.commons.io.monitor.FileAlterationObserver
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor
import java.io.File
import cats.instances.list
import java.util.concurrent.CancellationException
import scala.util.Failure
import scala.util.Random

/**
  * instead of creating a Future as a read-only structure
  * we may create it through a `Promise` as a write-only structure
  *    over its Future (one to one relation) such as it will complete when Promise gets written.
  *
  * As the dual of the Future it also holds a Try[T], value we can fulfill with a value or an exception
  *
  * Promise future is evaluated on demand, not eager as a Future
  *
  * TODO but the question now is how do I create
  * i.e a query to db that gives back a promise.
  *
  *
  *
  * Let's see an example
  */


object Promises extends App {

  implicit val ec =
    ExecutionContext.fromExecutor(Executors.newFixedThreadPool(3))

  val p1 = Promise[String]()
  p1.future.foreach(x => println(s"finally $x"))

  println("waiting till the promise gets real!!")
  //recommended to be explicit about it
  blocking { Thread.sleep(3000) }

  //TODO create callback API
  // maybe UI swing kind of thing
  p1.complete(Success("doing my job"))

}
object Promises2 extends App {

  implicit val ec =
    ExecutionContext.fromExecutor(Executors.newFixedThreadPool(3))

  def factorial(n: Int) = (BigInt(1) to BigInt(n)).product

  val p1 = Promise[Int]()

  // This is saying when promise is completed run a future taking the value from
  // the promise p1 and passing it to factorial
  p1.future.map(factorial).foreach(res => println(res.toString().size))

  println("still lazy")
  blocking { Thread.sleep(3000) }

  p1.complete {
    println("triggering future")
    Success(300000)
  }

  //equivalent to
  //bare in mind can't be completed twice
  // p1.success{
  //   println("triggering future")
  //   300000
  // }

}

/**
  * Not sure wha't the benefit here
  */
object PromiseWrapperPattern extends App {

  implicit val ec =
    ExecutionContext.fromExecutor(Executors.newFixedThreadPool(3))

  def myNewFuture[T](block: => T)(implicit ec: ExecutionContext): Future[T] = {
    val p = Promise[T]
    ec.execute {
      new Runnable() {
        def run(): Unit = {
          try {
            p.success(block)
          } catch {
            case NonFatal(ex) => p.failure(ex)
          }
        }
      }
    }
    p.future
  }

}

/**
  * similar to above
  * This requires a monitor library. In our case Apache Commons I/O
  *   "commons-io" % "commons-io" % "2.6"
  *
  */
object PromiseWrapperPattern2 extends App {
  implicit val ec =
    ExecutionContext.fromExecutor(Executors.newFixedThreadPool(3))

  //monitor multiple creations
  def fileCreated(directory: String): Future[String] = {
    val p = Promise[String]
    val fileMonitor = new FileAlterationMonitor(1000)
    val observer = new FileAlterationObserver(directory)
    val listener = new FileAlterationListenerAdaptor {
      override def onFileCreate(file: File): Unit =
        try p.trySuccess(file.getAbsolutePath())
        finally fileMonitor.stop()
    }
    observer.addListener(listener)
    fileMonitor.addObserver(observer)
    fileMonitor.start()
    p.future
  }

  fileCreated(".").foreach(println)

}
object FutureWithDelay extends App {

  import java.util._
  private val timer = new Timer(true)

  def timeout[T](millis: Long, block: => T): Future[T] = {
    val p = Promise[T]
    timer.schedule(new TimerTask {
      def run(): Unit = {
        try {
          p.success(block)
        } catch {
          case NonFatal(ex) => p.failure(ex)
        } finally {
          timer.cancel()
        }
      }
    }, millis)
    p.future
  }

  implicit val ec =
    ExecutionContext.fromExecutor(Executors.newFixedThreadPool(3))

  timeout(3000, Promises2.factorial(3000)).foreach(println)

  while (true) {}

}

/**
  * From concurrency in practice
  * convoluted but maybe good as first approach
  *
  * The idea is that you need a block to run and a promise
  * to pass it's future to this block so you can keep the promise
  * The purpuse of this promise is that it will be the trigger to cancel.
  * Last thing is wrapping block and promise in a Future.
  */
object CancelFutures extends App {

  implicit val ec =
    ExecutionContext.fromExecutor(Executors.newFixedThreadPool(3))

  //This Promise is Unit because we won't use its future
  // as usual. We are not interested in its value or exception... elaborate
  type Cancellable[T] = (Promise[Unit], Future[T])

  //this Future[Unit] aims to pass the cancellabe trigger to our current block of code
  def cancellable[T](block: Future[Unit] => T): Cancellable[T] = {
    val cancellationPromise = Promise[Unit]
    //this future we aim to make cancellable
    //Eager as usual
    val future = Future {
      // in here we pass the cancellable trigger to our block
      val result = block(cancellationPromise.future)
      //we tryFailure to only fail if not yet completed the future
      // if already completed then cancellation is not possible
      // thus CancellationException
      if (!cancellationPromise.tryFailure(new Exception("stop it")))
        throw new CancellationException
      result
    }
    (cancellationPromise, future)
  }

  //let's use it. Remember is is eager
  // now maybe is more clear the syntax: we needed a function from Future[Unit] to T
  //   to be sure we can pass the cancellationPromise.future to it
  //    and that's our trigger
  val (cancellationPromise, future) = cancellable { cancellator: Future[Unit] =>
    var i = 0
    while (i < 5) {
      if (cancellator.isCompleted) throw new CancellationException
      Thread.sleep(500)
      println(s"iteration $i .. still working")
      i += 1
    }
    "resulting value"
  }

  // just to show the side effect
  future.onComplete {
    case Success(value) => {
      println(s"Success is my name, and my value: $value")
    }
    case Failure(exception) => {
      println(s"I never fail but $exception")
    }
  }

  //let's run this
  Thread.sleep(1500)
  //trySuccess is the cancellation call, next time the Future evaluates is Completed will throw CancellationException
  val isCancelled = cancellationPromise.trySuccess(
    "remember is Unit, this text is not being processed"
  )
  println("computational  cancelled!")
  Thread.sleep(1000)

  //bare in mind that will eventually cancelled it's not deterministic
  // is not even guaranteed will be cancelled. You  could check it though with:
  println(s" is cancelled? $isCancelled")
  System.exit(0)

}

/**
  * From Victor
  * https://stackoverflow.com/questions/16020964/cancellation-with-future-and-promise-in-scala/16050595
  */
object CancelFutureElegant extends App {

  def cancellableFuture[T](
      block: Future[T] => T
  )(implicit ec: ExecutionContext): (Future[T], () => Boolean) = {
    val cancellablePromise = Promise[T]()
    val future = cancellablePromise.future
    cancellablePromise.completeWith(Future(block(future)))
    (future, () => cancellablePromise.tryFailure(new CancellationException))
  }

  implicit val ec =
    ExecutionContext.fromExecutor(Executors.newFixedThreadPool(3))

  val (futureResult, cancellator) = cancellableFuture[Int]( trigger => {
    var i = 0
    while (!trigger.isCompleted && i < 10) {
      Thread.sleep(500)
      println(s"iteration $i .. still working")
      i += 1
    }
    i
  })


  Thread.sleep(1500)
  println("let's  cancel!")
  futureResult.onComplete{
    case Success(value) => println(value)
    case Failure(exception) => println(exception)
  }
  val isCancelled = cancellator()
  //println(s"computational cancelled is: $isCancelled")
  Thread.sleep(10000) // will let show the work Future is still doing 
  System.exit(0)
}
