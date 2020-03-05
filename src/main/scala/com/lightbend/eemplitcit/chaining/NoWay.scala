package com.lightbend.eemplitcit.chaining

object NoWay extends App {

  implicit def AtoB(a: A) = B(a.a)
  implicit def BtoC(b: B) = C(b.b)

  val c = new A("a")

  println(c)

}

case class A(a: String)
case class B(b: String)
case class C(c: String)




