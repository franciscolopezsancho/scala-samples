package com.lightbend.pathdependent.outside

import com.lightbend.pathdependent.inside

class Bench extends App {

  //not only add to the package it self

  def run: Unit =  {

    val kenia = new inside.Country
    val uganda = new inside.Country
    val nairobi = new kenia.Capital(200)

    def requestTaxes(country: inside.Country)(capital: country.Capital): Int =
      capital.taxes



    println(requestTaxes(kenia)(nairobi))
    println(requestTaxes(uganda)(nairobi)) // Intellij won't show up
  }



}

//Better example
class Country {
  case class Capital(taxes: Int)

}
