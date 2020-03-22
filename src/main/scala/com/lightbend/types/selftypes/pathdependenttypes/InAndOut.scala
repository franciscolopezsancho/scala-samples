package com.lightbend.types.selftypes.pathdependenttypes


/**
 * This existential allow us to link two classes inner an outer
 * at compilation time.
 *
 * When passing an inner type of class
 * to the outer will check if it belongs to the outer instance. This is
 * a way to express that a instance of a class belongs to a context,
 * namely the outer instance class. Again, at compilation time.
 */
object InAndOut extends App {

  class NetworkSwitch(val name: String) { thisSwitch =>

    case class Port(nr: Int)

    //TODO would be more clarifying a sample with state in NetworkSwitch?
    def switchPortOn(port: Port): Unit = {
      println(s"Switching on Port $port")
    }
  }


    val networkSwitchA = new NetworkSwitch("nA")
    val networkSwitchB = new NetworkSwitch("nB")
    val port1 = new networkSwitchA.Port(1)

    //checks port1 belongs to networkSwitchA
    networkSwitchA.switchPortOn(port1)

    // WOULD FAIL COMPILATION
    // checks port1 don't belongs to networkSwitchB
    // networkSwitchB.switchPortOn(port1)

    // TODO add test to failed compilation

  /**
   * This would be the inverse having just the inner instance
   * we can recover the outer class
   * @param name
   */
  class KeepOuter(val name: String) { outer =>

    case class Inner(nr: Int){
      val context = outer
    }

  }

  def switchOnAnyNetwork(inner: KeepOuter#Inner): Unit = {
    println(s"This is my outer ${inner.context}")
  }

  val outer = new KeepOuter("outer")
  val inner = new outer.Inner(1)


  switchOnAnyNetwork(inner)

}



