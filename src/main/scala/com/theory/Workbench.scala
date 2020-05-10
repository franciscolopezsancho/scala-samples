package com.theory

import cats.Eval
import cats.data.Reader
import scala.annotation.tailrec
import concurrent._
import concurrent.duration._
import java.util.concurrent.Executors
import scala.concurrent.java8.FuturesConvertersImpl.P

object Workbench extends App {

  def tuple[U,V](u: U, v: V)(implicit ev: V <:< U): Unit = {
     println(u)
     println(v)
  }

  tuple(42,42)


  implicit class OptionOption[T](val oo: Option[Option[T]]) extends AnyVal {
    def flattenMe: Option[T] = oo.flatMap(each => each)
  }




}
