package com.lightbend.eemplitcit.implicitscope

object BeforePimp extends App{

  import XX._
  //companion objects of the type
  println(s"ttttt $f")
  //therefore
  println(s"iiiiii $i")



}




trait TU {
  implicit val i: Int = 8
}
object XX extends TU {
  implicit val s: String = "hello, world\n"
  def f(implicit s: String) = implicitly[String] * implicitly[Int]
}





