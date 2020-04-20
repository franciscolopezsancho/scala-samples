package com.theory.implicits.conversion.valueclass

import java.time.Period

// This is Custom Value Classes
// They provide not only conversion but inlining, avoiding to create objects
package object convenient {

  implicit class intToPeriod(val intValue: Int) extends AnyVal {
    def days =  Period.ofDays(intValue).getDays
  }

}







