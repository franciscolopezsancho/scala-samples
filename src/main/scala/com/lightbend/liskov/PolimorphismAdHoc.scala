package com.lightbend.liskov

object PolimorphismAdHoc {

  class Animal
  class Bird extends Animal

  def sayHi(animal: Animal): String =
    "Hi, I'm an Animal"

  def sayHi(bird: Bird): String =
    "Hi, I'm a Birdl"
  
  //whereever you expect a type as subtype should work
  // but has the type or as the subtype??
  def run: Unit = {
    val creature: Animal = new Bird()
    println(sayHi(creature))
  }
}
