package com.theory.implicits.chaining
import scala.language.implicitConversions

object Chain extends App {


  implicit def AtoB[T](a: T)(implicit tToA: T => A) = new B(a.a)
  implicit def BtoC[T](b: T)(implicit bToC: T => B) = new C(b.b)


  //would not compile
  val noWay: C = new A("a")

  // now AtoB is used as implicit param while
  // BtoC as an implicit conversion

  case class A(a: String)
  case class B(b: String)
  case class C(c: String)

  //teaser question: Why is T needed?

}
