//package com.lightbend.implicits.parameters.typeclasses.conversion
//
//object  ConversionTypeClasses1 extends App {
//
//
// import Diccionary1._
// import ImplicitConversions1._
//
// println(List("a", "bc", "de").sumAll)
// println(List(1, 2, 3).sumAll)
//
//
//}
//
//
//object ImplicitConversions1 {
//
// implicit class MonoidOps[A](list: List[A]) {
//  def sumAll(implicit monoid: Monoid[A]): A = {
//   if (list.isEmpty) monoid.empty
//   else monoid.combine(list.head,list.sumAll(monoid))
//  }
// }
//
//}
//
//trait Semigroup[A] {
// def combine(x: A, y: A): A
//}
//trait Monoid[A] extends Semigroup[A] {
// def empty: A
//}
//
//object Diccionary1 {
//
// implicit object stringMonoid extends Monoid[String] {
//
//  override def combine(x: String, y: String): String = x.concat(y)
//  override def empty: String = ""
// }
//
// implicit object intMonoid extends Monoid[Int] {
//
//  override def combine(x: Int, y: Int): Int = x + y
//  override def empty: Int = 0
// }
//
//}
