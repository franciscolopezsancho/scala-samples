package com.lightbend.implicits.scope.implicitscope

class Pimp {


}

object Pimp {

  implicit def doubleOps(double: Double): DoubleOps = new DoubleOps(double)
}

class DoubleOps(val double: Double) extends AnyVal {

  def isZero: Boolean = double == 0
}

