package com.theory.implicits.scope.currentscope

// a member of an enclosing template
object EnclosingTemplate extends App {

  implicit val b = new B 

  def run(implicit m: B) = {
    //not prefix
    println(m.hello)
  }

  run

}

class B {
  implicit val hello = "hello"
}

