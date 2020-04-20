package com.theory.packagess

package adapter

import java.util.concurrent.Executors

import scala.concurrent.{Await, ExecutionContext, Future}

/**
 * package com.lightbend.packagess
 * package adapter
 *
 * Allow you two things:
 * 1. refer to deeper packages without referencing from the beginning of the path. 
 *      @see line 25
 * 2. refer not only to its base package members `com.lightbend.packagess` but also to a specific one `adapter`
 *      @see line 27
 * Generally, if your project consists of multiple subpackages, it's a good idea to use a first package clause that names the project as a whole and is followed by a second package clause naming the current subpackage.
 * That way, you can refer to other subpackages of your project without the often long prefix that indicates the project.
 */
object Punchline extends App {
    import scala.concurrent.duration._

    //1. handy
    println(level.two.DeepDown)

    //2. flexible
    println(adapterMethod)
    println(commonMethod)



}
