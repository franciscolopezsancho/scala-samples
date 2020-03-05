package com.lightbend.eemplitcit.typeclasses

object TypeClasses0 extends App {

  import ImplicitLameShow._

    println(s" lameshow ${intShow.show(1)}")
}

trait Show0[A] {
  def show(a: A): String
}

object ImplicitLameShow {

  implicit val intShow = new Show0[Int]{
    override def show(valueToShow: Int): String = {
      valueToShow match {
        case 1 => "one"
        case 2 => "two"
        case _ => valueToShow.toString
      }
    }
  }
}



