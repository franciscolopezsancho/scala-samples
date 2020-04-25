package com.theory.implicits.chaining
import scala.language.implicitConversions

object Chain extends App {


  implicit def AtoB[T](a: T)(implicit tToA: T => A) = new B(a.a)
  implicit def BtoC[T](b: T)(implicit tToB: T => B) = new C(b.b)


  //would not compile
  val noWay: C = new A("a")

  // now AtoB is used as implicit param while (as aToA it's in scope :)
  // BtoC as an implicit conversion. So first converts and then looks for the implicit param

  case class A(a: String)
  case class B(b: String)
  case class C(c: String)

  //teaser question: Why is T needed?

}
