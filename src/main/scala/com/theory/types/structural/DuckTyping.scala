package com.theory.types.structural

/**
 * scala types are referred by Name. nominal typing
 * we can emulate structural typing that is creating a type merely defined by
 * it's structure. Put it differently. By its signature.
 * 
 *
 * */
class DuckTyping {
  type Closeable = { def close(): Unit } //print =>
                                          // type Closeable = scala.AnyRef {
                                          // def close(): Unit
                                          // : <notype>     , TODO explain notype


  val c1: Closeable = new java.io.StringWriter

}
