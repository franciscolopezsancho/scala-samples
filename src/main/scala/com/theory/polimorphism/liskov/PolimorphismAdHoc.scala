package com.theory.liskov


/**
 * also called METHOD OVERLOADING
 *
 * will help you to CHANGE your IMPLEMENTATION selected depending on types you pass to a function
 * it's EARLY BINDING (therefore the TYPE of the method that will be called will be decided at compile time)
 * RISK when receiving/passing a higher kind as there's !!!look out TYPE ERASURE
 * in scala this is often found as 'TYPECLASS PATTERN' ???
 */
object PolimorphismAdHoc extends App {

  class Animal
  class Bird extends Animal

  def hi(animal: Animal): String =
    "Hi, I'm an Animal"

  def hi(bird: Bird): String =
    "Hi, I'm a Birdl"
  
  //whereever you expect a type as subtype should work
  // but has the type or as the subtype?? EARLY vs LATE binding
    val creature: Animal = new Bird()
    println(hi(creature))
}
