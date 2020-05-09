package com.theory

import cats.Eval
import cats.data.Reader
import scala.annotation.tailrec
import concurrent._
import concurrent.duration._
import java.util.concurrent.Executors
import scala.concurrent.java8.FuturesConvertersImpl.P

object Workbench extends App {

  @scala.annotation.tailrec
  def factorial(n: Int, acc: BigInt = 1): BigInt =
    if (n == 0) acc else factorial(n - 1, n * acc)

  def factorial(n: Int): BigInt =
    if (n == 1) 1
      else n*factorial(n - 1)

  factorial(100)

}
