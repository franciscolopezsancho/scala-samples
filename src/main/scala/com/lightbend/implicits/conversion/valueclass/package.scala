package com.lightbend.implicits.conversion

import java.time.Period

//THIS is a CUSTOM typeclass
// This is actually Custom Value Classes
// TODO its only differenct with a normal type class is that is @inline?
      /// nope this is different also be
/// This is not extensible as there's no trait to follow
/// I can't refer to the trait itself
package object valueclass {

  implicit class intToPeriod(val intValue: Int) extends AnyVal {
    def days =  Period.ofDays(intValue).getDays
  }

}







