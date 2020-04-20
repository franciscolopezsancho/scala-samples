package com.theory.implicits.conversion

import java.time.Period

/**
 * The moto is trading type for value @see line 17
 *
 *
 * 2004. Conversion are the beginning of implicits, just a wrapper
 * implicit def c2T(c: C):T
 */
object Conversions {

  implicit def intToPeriod(intValue: Int): Period =
     Period.ofDays(intValue)

  val x: Period = 7

  x.getDays

}
