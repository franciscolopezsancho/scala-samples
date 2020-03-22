package com.lightbend.future.basics

import java.util.concurrent.Executors

import scala.concurrent.{ExecutionContext, Future}


object Fors extends App {


  //no tail recursive
  def factorial(n: Int) = (BigInt(1) to BigInt(n)).product


  implicit val ec = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(10))

  val f = Future {
      factorial(200000)
  }

  println("meanwhile I'm doing somehing else")

  // see callback inside (onComplete)
  // see Callback.scala next exercise
  f.foreach(v => println(s"foreach $v"))
  f.map(_.toString.size).onComplete(v => println(s"map $v"))
  f.flatMap(result => Future(result.toString.size)).onComplete(v =>println(s"flatMap $v"))
  f.filter(_ > BigInt(Long.MaxValue)).onComplete(v => println(s"filter, it was bigger "))

  //btw not deterministic



}
