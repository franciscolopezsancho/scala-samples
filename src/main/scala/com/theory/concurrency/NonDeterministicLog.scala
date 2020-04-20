package com.theory.concurrency

object NonDeterministicLog extends App {

  def threadThis(body: => Unit): Thread = {
    val t = new Thread {
      override def run() = body
    }
    t.start()
    t

  }

  val t = threadThis {
    println("1")

  }
  println("2")
  println("3")
  t.join()
  println("4")

}

/**
  * One way communication. Caller waits till called is done and then prints
  */
object ThreadCommunicationOneWay extends App {
  var result: String = null
  val t = NonDeterministicLog.threadThis { result = "\ntitle\n" + "=" * 5 }
  t.join()
  println(result)
}

/**
  * Non deterministic in a bad way.
  */
// object ThreadSharedStateAccessReordering extends App {
//   for (i <- 0 until 100000) {
//     var t1IsOn = false
//     var t2IsOn = false
//     var t1Wins = false
//     var t2Wins = false
//     val t1 = NonDeterministicLog.threadThis {
//       t1IsOn = true
//       t1Wins = if (!t2IsOn) true else false
//     }
//     val t2 = NonDeterministicLog.threadThis {
//       t2IsOn = true
//       t2Wins = if (!t1IsOn) true else false
//     }
//     t1.join()
//     t2.join()
//     assert((t1Wins || t2Wins), s"We have a winner")
//     assert(!(t1Wins && t2Wins), s"We have TWO a winner")
//   }
// }
object ThreadSharedStateAccessReordering extends App {
  for (i <- 0 until 1000000) {
    var a = false
    var b = false
    var x = -1
    var y = -1
    val t1 = NonDeterministicLog.threadThis {
      a = true
      y = if (b) 0 else 1
    }
    val t2 = NonDeterministicLog.threadThis {
      b = true
      x = if (a) 0 else 1
    }
    t1.join()
    t2.join()
    assert(!(x == 1 && y == 1), s"x = $x, y = $y")
  }
}
