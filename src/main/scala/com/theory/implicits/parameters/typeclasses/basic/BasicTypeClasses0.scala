package com.lightbend.implicits.parameters.typeclasses.basic

/**
  * The problem
  * class MyClass
  *
  * in order to add functionality inherit
  *
  * class MyClass extends Encodable (developer X want to encode)
  *    with Serializable (developer Y want to Serialize)
  *    with Comparable[MyClass] (developer Z wants to Compare)
  *    ... messy
  *
  *
  *
  * 2. class ClassNotMine (i.e. external library)
  *  -> there's no way around it
  *
  *
  *  A type class is the combination of
  *   1. generic requirements => trait with parametric polimorphism
  *   2. with implicit passed dictionaries => the instances of that trait
  *   [3. implicit conversion is handy but not required]
  *  A type class is built with a implicit conversion plus a dictionary passed as implicit parameter
  *
ad-hoc polimorphism  (overloading) and parametric polimorphism without subtyping
great definition in https://typelevel.org/cats/typeclasses.html
  *
  *  Here we show the implicit dictionary
  *
  *  @link https://www.youtube.com/watch?v=uPd9kJq-Z8o Martin Odersky talk
  *  https://www.scala-lang.org/old/sites/default/files/odersky/wg2.8-boston06.pdf Martin Odersky paper when creating type classes
  *  https://ropas.snu.ac.kr/~bruno/papers/TypeClasses.pdf paper comparing with Haskell
  */
object BasicTypeClasses0 extends App {

  import EnglishDictionary._
  import ImplicitConversions00._

  println(showing(true))
  println(showing(1))

}
// is this the type class or the above

trait Show[A] {
  def show(x: A): String
}

/**
  *  the type class
  *   first role
  *    is to define requirements for type parameters used by generic algorithms
  *   second role
  *    is to propagate constraints automatically
  */
object ImplicitConversions00 {

  //TODO do I need conversion?
  def showing[T](a: T)(implicit myTrait: Show[T]): String =
    myTrait.show(a)

}

object EnglishDictionary {

  implicit val booleanShow: Show[Boolean] = new Show[Boolean] {

    override def show(x: Boolean): String =
      if (x) "true" else "false"
  }

  implicit val intShow: Show[Int] = new Show[Int] {

    override def show(x: Int): String =
      x match {
        case 0 => "zero"
        case 1 => "one"
        case _ => "not sure"
      }
  }

}
