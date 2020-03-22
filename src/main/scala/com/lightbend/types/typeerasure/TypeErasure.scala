package com.lightbend.types.typeerasure

import scala.reflect.runtime.universe.{TypeTag, typeOf}

object TypeErasure extends App {

  List(1, 2) match {
    case b: List[String] => println("list of string")
    case a: List[Int] => println ("list of int")
  }

  def myMatcher[A : TypeTag](list: List[A]): Unit = {
    typeOf[A] match {
      case t if t =:= typeOf[String] =>  println("list of string")
      case t if t =:= typeOf[Int] =>  println("list of int")
    }
  }

  myMatcher(List(1,2))

}
