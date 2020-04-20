package com.lightbend.implicits.parameters.typeclasses.basic

object BasicTypeClasses1 extends App{

  import Diccionary._
  import TypeClassA._

  println(sum(List("a", "bc", "de")))
  println(sum(List(1, 2, 3)))

}
// TODO is this the type class or the above
// I guess the question
object TypeClassA {

  def sum[T](list: List[T])(implicit monoid: Monoid[T]): T =
    if (list.isEmpty) monoid.empty
    else monoid.combine(list.head, sum(list.tail)(monoid))

}

trait Semigroup[A] {
  def combine(x: A, y: A): A
}
trait Monoid[A] extends Semigroup[A] {
  def empty: A
}

object Diccionary {

  implicit val stringMonoid: Monoid[String] = new Monoid[String] {

    override def combine(x: String, y: String): String = x.concat(y)
    override def empty: String = ""
  }

  implicit val intMonoid: Monoid[Int] = new Monoid[Int] {

    override def combine(x: Int, y: Int): Int = x + y
    override def empty: Int = 0
  }

}
