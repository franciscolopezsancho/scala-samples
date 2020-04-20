package com.theory.dsl


/**
 * Two components are needed to create custom dsl
 *
 * by name params
 *
 *
 **/
object Components extends App {


  case class MyOption(value: String) {

    def getOrElseLazy(default: => String): String =
      if (value.isEmpty) default else this.value


    def getOrElseEager(default: String): String = {
      if (value.isEmpty) default else this.value
    }

  }

  //note this has to be a def with no input param
  def goFetchMyValue: String = {
    println("I'm an expensive operation")
    Thread.sleep(3000)
    "here I'am "
  }


  MyOption("Failing to avoid expensive operations").getOrElseEager(goFetchMyValue)

  MyOption("Avoiding expensive operations").getOrElseLazy(goFetchMyValue)

}
