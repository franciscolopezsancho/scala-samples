package com.theory.xfiles

package com.theory.xfiles

package com.theory

package com.theory


/**
 *  Call me Heissenberg
  * Stops around 7k if not monitored with JMX. 
  */
object Workbench extends App {

  Thread.sleep(10000)

  def threadThis[A](block: => A) = {
    val t = new Thread {
      override def run(): Unit = {
        block
      }
    }
    t.start()
    t
  }

  class SyncVar[T](implicit ord: Ordering[T]) {
    private var v = null.asInstanceOf[T]
    def get(): T = this.synchronized{
      v
    }
    def put(x: T): Unit = this.synchronized{
      val i = implicitly[Ordering[T]]
    
      if(v == null || i.lt(get(),x)){
        v = x
      }
    }
  }

 
  for (i <- 1  to 10000){
     val s  = new SyncVar[Int]
    val t1 = threadThis(s.put(3))
    val t2 = threadThis(s.put(4))

    t1.join()
    t2.join()
    println(i)

  }


}