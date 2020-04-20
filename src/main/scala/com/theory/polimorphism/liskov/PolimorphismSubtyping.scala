package com.theory.liskov

/**
 * METHOD OVERRIDING @see line 26
 *
 * LATE BINDING
 * GOOD for ADT easy to add new data without modifying existing code?
 *    but you'll break all the pattern matching
 * LIMITATIONS
 *    only one implementation per type
 *    complex/inflexible hierarchies
 *        as when you want to change the supertype all subtypes will be affected
 *        something else?
 *    return-current-type problem?
 */
object PolimorphismSubtyping extends App {

  trait Animal {
    def name: String
    def hi: String = s"my Animal name is $name"
  }

  //remember here a field param creates a getter
  //remember herer def -> val is allowed, not the inverse
  class Bird(val name: String) extends Animal {
    override def hi: String = s"my Bird name is $name"
  }

 
  val johnA: Animal = new Bird("john")
  val jimB: Bird = new Bird("jim")

  //late binding or early binding?
    println(johnA.hi)
    println(jimB.hi)
}
