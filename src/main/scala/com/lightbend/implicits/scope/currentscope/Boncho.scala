package com.lightbend.implicits.scope.currentscope

// imported identifiers accesible without prefix
object Boncho extends App {

  import Aa._


  def run(implicit m: Aa) = {
    //not prefix
    println(hello)
  }

  abstract class Aa {
    def hello
  }

  object Aa {
    implicit def hello = "hello"
  }


}


abstract class Aa {
  def hello
}

object Aa {
  implicit def hello = "hello"
}



