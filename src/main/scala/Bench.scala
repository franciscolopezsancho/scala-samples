class ValueClass(val value: String) extends AnyVal {
  def add(v: ValueClass): ValueClass =
    new ValueClass(value + v.value)
}

class Meter(val value: Double) extends AnyVal {
    def +(m: Meter): Meter = new Meter(value + m.value)
}

class Bench {

  def run: Unit =  {
    val vc1 = new ValueClass("hello ")
    val vc2 = new ValueClass("world")

    println(vc1 add vc2)

    val x = new Meter(3.4)
    val y = new Meter(4.3)
    val z = x + y
    println(z)
  }



}
