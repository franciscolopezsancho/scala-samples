package com.lightbend.selftypes

object Bench extends App {



   def run: Unit = {

    println("hi")
     val house = new Loft
     val myRoom = new house.Room
     println(myRoom.peopleOutside)
  }


}
// Better example with no spatial
// COULDN'T refer to the outer through THIS
class Loft { outer =>
  val peopleOut: Int = 2
  class Room {
    val people: Int = 1
    val peopleOutside: Int = outer.peopleOut - people
  }
}

// or mix in
//? why not just mix in?
class Room {
  val peopleInRoom: Int = 1
}
class Flat {  this:Room =>
  val peopleInTheGarden: Int = 1
  val peopleAtFlat: Int = peopleInRoom + peopleInTheGarden
}

// let's try jus mixin
class Bungalow extends Room {
  val peopleInTheGarden: Int = 1
  val peopleAtFlat: Int = peopleInRoom + peopleInTheGarden
}
