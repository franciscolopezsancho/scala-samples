package com.theory.future.sequence

import java.time.LocalDateTime
import java.util.concurrent.Executors

import scala.concurrent.{ExecutionContext, Future}
/**
  * Can transform a Iterable[A] to an Future[Iterable[B]]
  * i.e. list of users, iterate over them to request a DB info about them
  * bare in mind that would give us a List[Future[A]]. Traverse will sequence that.
  */
object Traverse extends App{


  implicit val ec = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(4))

  println(s" seconds of this minute ${LocalDateTime.now().getSecond}")

  val list = List(1000,2000,3000)

  def task(durationMillis: Int) = {
    Thread.sleep(durationMillis)
    println(s"took me $durationMillis")
    durationMillis
  }

  Future.traverse(list)( each =>  Future(task(each))).onComplete{
    x => 
    println(s" $x took till this second of this minute: ${LocalDateTime.now().getSecond}")
    System.exit(0)
  }



}


object MyTraverse extends App {
    
  implicit val ec = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(4))

  println(s" seconds of this minute ${LocalDateTime.now().getSecond}")

  val list = List(1000,2000,3000)

  def task(durationMillis: Int) = {
    Thread.sleep(durationMillis)
    println(s"took me $durationMillis")
    durationMillis
  }

    list.map( each => Future(task(each)).onComplete(
      x => 
      println(s" $x took till this second of this minute: ${LocalDateTime.now().getSecond}")
    )) 
    
  

}
