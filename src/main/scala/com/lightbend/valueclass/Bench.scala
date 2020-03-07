object Implicits {
  implicit class ValueClass(val value: Int) extends AnyVal {

    def toHex: String =
      value.toString + "HOX"
  }
}

class BenchImplicitScope {

  import Implicits._

  def run: Unit =  {
    println(2.toHex)
  }



}
