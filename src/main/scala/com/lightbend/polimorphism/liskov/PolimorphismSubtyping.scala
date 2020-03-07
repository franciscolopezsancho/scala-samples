package com.lightbend.liskov

object PolimorphismSubtyping {

  trait Animal {
    def name: String
    def hi: String = s"my Animal name is $name"
  }

  //remember here a field param creates a getter
  //remember herer def -> val is allowed, not the inverse
  class Bird(val name: String) extends Animal {
    override def hi: String = s"my Bird name is $name"
  }

  def sayHi(animal: Animal): String =
    animal.hi

  val johnA: Animal = new Bird("john")
  val jimB: Bird = new Bird("jim")

  //late binding or early binding?
  def run: Unit = {
    println(sayHi(johnA))
    println(sayHi(jimB))
  }
}
