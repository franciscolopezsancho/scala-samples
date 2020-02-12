class ValueClass(val value: Int) extends AnyVal {

  def toHex: String =
    value.toString + "HOX"
}
