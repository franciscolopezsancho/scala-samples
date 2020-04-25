package com.theory.polimorphism.variance

object ContravariantFunctor extends App {

  trait Printable[A] { self => 
    def format(value: A): String

    def contramap[B](func: B => A): Printable[B] =
      new Printable[B] { 
        def format(value: B): String =
          self.format(func(value))
      }
  }

  def format[A](value: A)(implicit p: Printable[A]): String =
    p.format(value)

  implicit val stringPrintable: Printable[String] =
    new Printable[String] {
      def format(value: String): String =
        "\"" + value + "\""
    }

  implicit val booleanPrintable: Printable[Boolean] =
    new Printable[Boolean] {
      def format(value: Boolean): String =
        if (value) "yes" else "no"
    }

  implicit def boxPrintable[A](implicit p: Printable[A]): Printable[Box[A]] = {
      p.contramap[Box[A]](_.value)
  }

  //// THE QUESTION!
  final case class Box[A](value: A)
  format(Box("hello world"))
    // res5: String = "hello world"
  format(Box(true))
    // res6: String = yes
}
