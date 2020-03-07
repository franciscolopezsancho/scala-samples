package com.lightbend
package adapter

class Bench {

  //not only add to the package it self

  def run: Unit =  {

    println(adapterMethod)
    // will not compile println(scaladslMethod)
  }



}
