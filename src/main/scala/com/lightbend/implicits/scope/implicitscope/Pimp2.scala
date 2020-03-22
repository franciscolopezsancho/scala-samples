package com.lightbend.implicits.scope.implicitscope


//Pimp means

object Pimp2 extends App   {


  println(implicitly[BB[AA]]) // error: ambiguous implicit values
//  println(implicitly[String])
//



}


trait AA {

  implicit val a: String = "hi"
}

trait BB[T]

object AA {
  implicit val ai: BB[AA] = new BB[AA] { override def toString = "A" }
  implicit val ab: String = "hi"
}

