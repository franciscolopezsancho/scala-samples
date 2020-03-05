package com.lightbend.eemplitcit.typeclasses

object TypeClasses1 extends App {

  //kind of nice
  import ImplicitsShowman._
  import ImplicitsShowman.ShowOps

  implicit val x = intShow

    //kind of nice
    println(s" showman ${1.show}")

}

// two way normal import
// package import
trait Show1[A] {
  def show(a: A): String
}

object ImplicitsShowman {

  //how comes with show[A] doesn't work?, show[A] and Show1[A] have different As right
  implicit class ShowOps[A](val a: A)  {
    def show(implicit showman: Show1[A]): String =
      showman.show(a)
  }

  val intShow = new Show1[Int]{
    override def show(valueToShow: Int): String = {
      valueToShow match {
        case 1 => "one"
        case 2 => "two"
        case _ => valueToShow.toString
      }
    }
  }
}


