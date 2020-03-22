//package com.lightbend.implicits.parameters.typeclasses.conversion
//
///**
// * The problem
// * class MyClass
// *
// * in order to add functionality inherit
// *
// * class MyClass extends Encodable (developer one want to encode)
// * with Serializable (developer two want to Serialize)
// * with Comparable[MyClass] (developer three wants to Compare)
// * ...
// *
// *
// *
// * 2. class ClassNotMine (i.e. external library)
// * -> there's no way around it
// *
// *
// * A type class are implicit passed dictionaries
// * A type class is built with a implicit conversion plus a dictionary passed as implicit parameter
// *
// *
// * Here we show the implicit conversion and the implicit diccionary
// */
//
//object ConversionTypeClasses0 {
//
//  import SpanishDiccionary._
//  import ImplicitConversions0._
//
//
//  true.showing
//  1.showing
//
//
//}
//
//trait Show1[A] {
//  def show(x: A): String
//}
//
//object ImplicitConversions0 {
//
//  implicit class ShowOps[A](a: A) {
//    def showing(implicit typeClass: Show1[A]): String =
//      typeClass.show(a)
//  }
// // TODO  how can I do this with just a method sort of...
// // implicit def showOps[A](a: A) = {
//  ////   def show(implicit typeClass: Show1[A]): String =
//  ////     typeClass.show(a)
//  //// }
//
//
//}
//
//
//object SpanishDiccionary {
//
//  implicit object booleanShow extends Show1[Boolean] {
//
//    override def show(x: Boolean): String =
//      if (x) "verdadero" else "falso"
//  }
//
//  implicit object intMonoid extends Show1[Int] {
//
//    override def show(x: Int): String =
//      x match {
//        case 0 => "cero"
//        case 1 => "uno"
//        case _ => "no se"
//      }
//  }
//
//
//
//
//}
