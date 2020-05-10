package com.theory.implicits.scope.implicitscope

class InheritanceYes extends A {

println(implicitly[String])

}


trait A {

  implicit val a: String = "hi"
}




