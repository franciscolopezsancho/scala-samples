class BenchValueClass {


    println(new ValueClassBasic(2))
    println(new NonValueClass(20))
    println(new ValueClassBasic(2).toHex)
    println(new NonValueClass(20).toHex)



}

// TODO ?? decompiling I don't see difference among two first
// second example of ValueClassBasic it actually shows how is just
// using extension method
class NonValueClass(val value: Int) {

  def toHex: String =
    value.toString + "HEX"

}

class ValueClassBasic(val value: Int) extends AnyVal {

  def toHex: String =
    value.toString + "HOX"
}
