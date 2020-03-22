package com.lightbend.dsl


/**
 * Two components are needed to create custom dsl
 *
 * by name params
 *
 *
 **/
object Components {


  case class MyOption(value: String) {

    def getOrElseLazy(default: => String): String =
      if (value.isEmpty) default else this.value


    def getOrElseEager(default: String): String = {
      if (value.isEmpty) default else this.value
    }

  }

  def goFetchMyValue: String = {
    println("I'm an expensive operation")
    Thread.sleep(3000)
    "here I'am "
  }


    MyOption("").getOrElseLazy("I'm ")

}
