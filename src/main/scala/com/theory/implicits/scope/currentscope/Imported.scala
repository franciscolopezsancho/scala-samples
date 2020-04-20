package com.theory.implicits.scope.currentscope

// imported identifiers accesible without prefix
object Imported extends App {

  import Aa._


    //not prefix
  println(hello)



}



object Aa {
  implicit val hello = "hello"
}



