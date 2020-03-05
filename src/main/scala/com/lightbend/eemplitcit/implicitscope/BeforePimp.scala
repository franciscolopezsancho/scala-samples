package com.lightbend.eemplitcit.implicitscope

object BeforePimp extends App{

  import X._
  //companion objects of the type
  println(s"ttttt $f")
  //therefore
  println(s"iiiiii $i")



}




trait T {
  implicit val i: Int = 8
}
object X extends T {
  implicit val s: String = "hello, world\n"
  def f(implicit s: String) = implicitly[String] * implicitly[Int]
}





