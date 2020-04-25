package com.theory.future.sequence

import java.time.LocalDateTime
import java.util.concurrent.Executors

import scala.concurrent.{ExecutionContext, Future}
import scala.concurrent.duration._
import scala.concurrent._
import scala.util.Success

/**
 * Future.sequence will transform a Seq[Future[A]] in a Future[Seq[A]]
 * over which can be later map or flatMapped
 */
object Sequence extends App{


  implicit val ec = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(3))

  println(s" seconds of this minute ${LocalDateTime.now().getSecond}")

  val list = List(1000,2000,3000)

  def task(durationMillis: Int) = {
    blocking{Thread.sleep(durationMillis)}
    println(s"took me $durationMillis")
    durationMillis
  }

  val futureTasks: Seq[Future[Int]] = list.map(each => Future(task(each)))

  Future.sequence(futureTasks).map(
    x => println(s" $x took till this second of this minute: ${LocalDateTime.now().getSecond}"))


  val x: Future[Seq[Int]] = Future.sequence(futureTasks).map( x => x.map(_ + 1))

  Await.result(x,4.seconds)
  System.exit(0)
}


object MyOwnSequence extends App {
  implicit val ec = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(2))
  val futuresList =  List(Future(1),Future(2))

  val listFutures = futuresList.foldLeft(Future.successful(List.empty[Int])){
    case (acc,curr) => val res = for {
      a <- acc
      c <- curr
    } yield {
      c +: a
    }
    res
  }

  listFutures.map(println)
  Thread.sleep(100)
  System.exit(0)
}
