package com.theory.implicits.scope.implicitscope

// Trait CanFoo brings it's companion object
// Bare in mind just CanFoo is referred below
// This technique is widely used in combination with TypeClasses
package object typeinquestion {

    def foo[A:CanFoo](x: A): String = implicitly[CanFoo[A]].foos(x)

    def unsugaredFoo[A](x: A)(implicit canFoo: CanFoo[A]): String = canFoo.foos(x)

    def f(implicit s: String): Map[Int,String] = ???

}