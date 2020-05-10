package com.theory.implicits.scope.currentscope

/**
  * It would not work for class
  *
  */
class InheritanceNot extends Bob {


 //println(implicitly[String]) 

}

class Bob {
   implicit val hello = "hello"
}
