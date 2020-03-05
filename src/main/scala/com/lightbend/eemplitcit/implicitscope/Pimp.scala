package com.lightbend.eemplitcit.implicitscope

class Pimp {

  val cageX = new Cage[X]{
    println(s"ttttt ${implicitly[Int]}")


  }
  //companion objects of the type
  println(s"iiiiii $i")


}

class Cage[A]


trait T {
  implicit val i: Int = 8
}

class X extends T {
  implicit val su: String = "hello, world\n"

  def f(implicit s: String) = implicitly[String] * implicitly[Int]
}
