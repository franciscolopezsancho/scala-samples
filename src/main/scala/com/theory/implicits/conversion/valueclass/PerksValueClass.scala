package com.theory.implicits.conversion.valueclass

import scala.collection.View.Single



/**
 * A value class adds a method wrapping an instance of a class while avoiding creating of
 * the wrapper. Contradictory?
 * 
 * To check the how is done @see Bytecode outline of:
 *     PerksValueClass.class, ValueClassBasic.class AND ValueClassBasic$.class ->running cfr.sh
 */

class PerksValueClass {


    //this class doesn't even get built. Is not allocating any object
    new ValueClassBasic(1)
    new NonValueClass(2)
    // In this case it get's inlined and creates an extension method. 
    // reducing the memory footprint. 
    // The extension method is basically sintactic sugar to add an static method to an object already compiled.
    // ValueClassBasic$.MODULE$ is a singleton. See Singleton.foo() at the end
    new ValueClassBasic(3).toHex
    new NonValueClass(4).toHex
    Singleton.foo()



}


class NonValueClass(val value: Int) {

  def toHex: String =
    value.toString + "HEX"

}

class ValueClassBasic(val value: Int) extends AnyVal {

  def toHex: String =
    value.toString + "HOX"
}

object Singleton {
  def foo(): String = "There can only be one."
}


// you may continue @ PackagingConvenience