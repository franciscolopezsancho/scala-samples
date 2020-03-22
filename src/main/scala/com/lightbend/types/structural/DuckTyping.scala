package com.lightbend.types.structural

/**
 * scala types are referred by Name. site-name typing
 * we can emulate site-def that is creating a type merely defined by
 * it's structure, this is. It's functions, signature.
 * Now we are posed we a problem. Are function defined by name and signature or just
 * signature. ... I think that defining anonymous function have this just by signature property
 *
 * */
class DuckTyping {
  type Closeable = { def close(): Unit } //print =>
                                          // type Closeable = scala.AnyRef {
                                          // def close(): Unit
                                          // : <notype>     , TODO explain notype


  val c1: Closeable = new java.io.StringWriter

}
