package com.theory.implicits.scope.currentscope

// a member of an enclosing template
object EnclosingTemplate extends App {

  implicit val b = "aloha" 


  def run(implicit m: String) = {
    //not prefix
    println(m)
  }

  run

}


