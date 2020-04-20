package com.theory.implicits.chaining

object NoWay extends App {

  implicit def AtoB(a: A) = B(a.a)
  implicit def BtoC(b: B) = C(b.b)

  val b: B = new A("a")

  //would not compile
  //val noWay:C = new A("a")

  case class A(a: String)
  case class B(b: String)
  case class C(c: String)
}
