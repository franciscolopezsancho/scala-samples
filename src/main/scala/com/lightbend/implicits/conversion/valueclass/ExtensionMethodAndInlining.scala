package com.lightbend.implicits.conversion.valueclass



/**
 * A value class add a method to a value while avoiding creating of
 * the wrapper. @see Bytecode outline -> cfr
 */

class BenchValueClass {


    new ValueClassBasic(2)
    new NonValueClass(20)
    // be aware just this one is inlined. Just when used the extension method
    new ValueClassBasic(2).toHex
    new NonValueClass(20).toHex



}


class NonValueClass(val value: Int) {

  def toHex: String =
    value.toString + "HEX"

}

class ValueClassBasic(val value: Int) extends AnyVal {

  def toHex: String =
    value.toString + "HOX"
}
