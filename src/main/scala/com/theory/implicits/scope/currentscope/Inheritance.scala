package com.theory.implicits.scope.currentscope

class Inheritance extends Bob {


  def run(implicit m: String) = {
    //not prefix
    println(hello)
  }

  

}

class Bob {
   implicit val hello = "hello"
}
