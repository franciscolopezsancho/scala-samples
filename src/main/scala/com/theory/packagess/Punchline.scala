package com.theory.packagess

package adapter

import java.util.concurrent.Executors


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

/**
 * a bit of history
 * before 2.8
 *  
 * package org {
 *       package myproject {
 *         package tests {
 *           ...
 *         }
 *       }
 *     }
 *  They all meant the same. In Scala 2.8 this has changed. The difference is that the package clause:
 * 
 *     package org.myproject.tests
 *  now only brings the members of the org.myproject.tests in scope, but not the members of the two outer packages org.myproject and org. If you want to have the members of both tests and org.myproject in scope you need to use the two nested package clauses above, or else the chained equivalent:
 * 
 *     package org.myproject // myproject members are visible, but not org members
 *     package tests         // test members are visible
 * 
 * 
 *  https://www.artima.com/scalazine/articles/chained_package_clauses_in_scala.html
 * 
 * 
 **/