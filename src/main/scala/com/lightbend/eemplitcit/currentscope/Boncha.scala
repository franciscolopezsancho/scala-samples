package com.lightbend.eemplitcit.currentscope

// a member of an enclosing template
object Boncha extends App {

  implicit def hello = "hello"

  def run(implicit m: Aa) = {
    //not prefix
    println(hello)
  }


}



