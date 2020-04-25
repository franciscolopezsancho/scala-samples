package com.theory.types.selftypes.pathdependenttypes


/**
  * 
  */
object Unsoundness extends App {

    trait BadUpper {type A >: String}
    trait BadLower {type A <: Int}

    def ooops0[T <: BadLower]: Any => T#A = (a: Any) => a.asInstanceOf[T#A]
    def ooops1[T <: BadUpper]: T#A => Nothing = (a: T#A ) => a.asInstanceOf[Nothing]


     
}
