package com.lightbend.packagess

/**
 * When define like this we can have access to this methods in any place
 * iside com.lightbend.adapter
 */
package object adapter {

  def adapterMethod: Unit =
    println("testfunc")
}
