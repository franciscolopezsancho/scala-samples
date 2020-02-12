class Bench {


  def run: Unit =  {
    println(new ValueClass(2))
    println(new NonValueClass(20))
    println(new ValueClass(2).toHex)
    println(new NonValueClass(20).toHex)
  }



}
