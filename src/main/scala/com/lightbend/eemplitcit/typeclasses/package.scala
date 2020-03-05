package com.lightbend.eemplitcit

import java.time.Period

//THIS is a CUSTOM typeclass
package object typeclasses {

  implicit class intToPeriod(val intValue: Int) extends AnyVal {
    def days =  Period.ofDays(intValue).getDays
  }


}







