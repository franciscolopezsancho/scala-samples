package com.theory.implicits.scope.implicitscope


//Pimp means

object Pimp2 extends App   {


  println(implicitly[BB[AA]]) // error: ambiguous implicit values
  
  def bab(implicit ba: BB[AA]): String = {
      s"found my implicit ba: $ba"
  }

  println(bab)
//



}


trait AA {

}





trait BB[T]


object AA {
  implicit val ai: BB[AA] = new BB[AA] { override def toString = "A" }
}



