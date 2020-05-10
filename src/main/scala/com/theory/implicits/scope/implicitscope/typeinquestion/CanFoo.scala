package com.theory.implicits.scope.implicitscope.typeinquestion


trait CanFoo[A] {
  def foos(x: A): String
}
object CanFoo {
  implicit val companionIntFoo = new CanFoo[Int] {
    def foos(x: Int) = "companionIntFoo:" + x.toString
  }
} 

