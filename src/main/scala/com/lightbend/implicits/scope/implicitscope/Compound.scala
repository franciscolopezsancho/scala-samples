package com.lightbend.implicits.scope.implicitscope

class Extends extends A {

println(implicitly[String])

}


trait A {

  implicit val a: String = "hi"
}




