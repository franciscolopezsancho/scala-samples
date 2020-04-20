package com.theory.dsl

import java.io.{ByteArrayOutputStream, PrintStream}


/**
 * One way to make a DSL is
 *  through custom abstractions
 *  another is through types
 */
object CustomAbstraction extends App {


// def withPrintStream[A](out: PrintStream)(block: => A): A = {
//     Console.withOut(out)(block)
// }
//
//  val outputRedirectionStream = new ByteArrayOutputStream()
//  val out = new PrintStream(outputRedirectionStream)
//  withPrintStream(out){println("Hello")}


//
//  def repeatWhile[A](condition: => Boolean)(block: => A): Unit  ={
//    if(condition) {
//      block
//      repeatWhile(condition)(block)
//    }
//  }

//  var x = 0
//  repeatWhile(x < 5) {
//    println(x)
//    x += 1
//  }

  /**
   *
   *  remember value classes. This new here is a value class
   *  This new is AnyRef
   */

  def repeat[A](block: => A)= new AnyRef {
    def until(condition: => Boolean): Unit = {
      block
      if(!condition) until(condition)
    }
  }

//  var y = 0
//  repeat {
//    println(y)
//    y += 1
//  } until(y >= 5)
//


}
