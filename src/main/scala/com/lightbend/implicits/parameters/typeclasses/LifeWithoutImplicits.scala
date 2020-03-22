package com.lightbend.implicits.parameters.typeclasses

/**
 *
 * @ see parametric prolimorphism
 *
 *
 * source https://www.scala-lang.org/old/sites/default/files/odersky/wg2.8-boston06.pdf
 */
class LifeWithoutImplicits {


  sum(List("a", "bc", "de"))(stringMonoid)
  sum(List(1, 2, 3))(intMonoid)


  def sum[T](list: List[T])(monoid: Monoid[T]): T =
    if (list.isEmpty) monoid.empty
    else monoid.combine(list.head, sum(list)(monoid))

  //mixer and waiter? why is difficult to think in use case?


}

trait Semigroup[A] {
  def combine(x: A, y: A): A
}

trait Monoid[A] extends Semigroup[A] {
  def empty: A
}


object stringMonoid extends Monoid[String] {

  override def combine(x: String, y: String): String = x.concat(y)

  override def empty: String = ""
}

object intMonoid extends Monoid[Int] {

  override def combine(x: Int, y: Int): Int = x + y

  override def empty: Int = 0
}
