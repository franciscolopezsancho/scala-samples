package com.lightbend.types.abstract_type_members


/**
 * limit the types while not defining them completely
 * putting an Upperbound. The fact that this type is not Food but subtype
 * this points to the ability to isolate us here from external types
 *
 * alternatives?
 *
 */
object AbstractTypeMembers extends App{

  class Cow extends Animal

  //TODO ? would this be recommended?
  //  after all the trait already limit the posibilities
  sealed trait Food
  case object Grass extends Food
  case object Grain extends Food

  abstract class Animal {
    type SuitableFood <: Food
    def eat(food: Food): Animal = this
  }

  class Bird extends Animal {
    override type SuitableFood = Grain.type
  }



  println(new Bird().eat(Grain).eat(Grain))

}
