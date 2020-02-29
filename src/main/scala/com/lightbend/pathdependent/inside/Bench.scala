package com.lightbend.pathdependent.inside

class Bench {

  //not only add to the package it self

  def run: Unit =  {

    val kenia = new Country
    val uganda = new Country
    val nairobi = new kenia.Capital(200)

    println(kenia.requestTaxes(nairobi))
    println(uganda.requestTaxes(nairobi))
  }



}

//Better example
class Country {
  case class Capital(taxes: Int)
  def requestTaxes(capital: Capital): Int =
    capital.taxes
}
