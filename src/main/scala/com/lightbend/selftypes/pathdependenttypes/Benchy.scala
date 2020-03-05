package com.lightbend.selftypes.pathdependenttypes

object Benchy extends App {

  // How to RE
  //is it the idea that without it I can't change an inner class?

  def run: Unit = {
    val networkSwitchA = new NetworkSwitch("nA")
    val networkSwitchB = new NetworkSwitch("nB")
    val port1 = new networkSwitchA.Port(1)
    val port2 = new networkSwitchA.Port(2)
    val port21 = new networkSwitchB.Port(1)

    switchOnAnyNetwork(port21)

  }


  def switchOnAnyNetwork(port: NetworkSwitch#Port): Unit = {
    println(s" switching off $port on Network ${port.switchName}")
  }


}


class NetworkSwitch(name: String) { thisSwitch =>
  case class Port(nr: Int){
    val switchName = thisSwitch.name
  }

  def switchPortOn(port: Port): Unit = {
    println(s"Switching on Port $port")
  }
}
