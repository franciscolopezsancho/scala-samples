package com.lightbend.variance

object PolimorphismParametric {

  class Animal
  class Bird extends Animal


  //let's try invariance, covariance and contravariance
  class Box[T]

  val a: Box[Animal] = new Box[Animal]




  //remember field params
  class Container[T](val content: T)
  
  //method content behaves as Covariant!!!!
  val b: Container[Animal] = new Container(new Bird)
  



  //conandrums

  //see Function0[+R]
  //see Function1[-T,+R]
  //why it does not complaing, after all is invariant in covarian/contravarian position
  class Cage[A,B](val content: A) {
    def getter: A = // covariant or invariant
      content
    def transform(a: A,f: A => B): B = // a is contravariant or invariant
      f(a)
    val animalLLL: A = // covariant or invariant
      content
    var animalRRR: A = // invariant or invariant
      content
  }

  //how can I pass a subtype in a contravariant position?
  class Parcel[T] {

    def boo(t: T): Unit =
      println("hello, world")
  }

  val bA = new Box[Animal] 
  // WILL NOT COMPILE bA.boo(new Bird())
  //answer this is subtypying
  //


  // variance is about the relation of COMPOSITE types, themselves, not values
  //    is about rules over the definition of the val,var,and defs under Parametric Polimorphism
  //    , no about the rules how to assign them nor use them
  //    Also Functions are COMPOSITE types
  // subtyping is about the relation of types, of the values used in val, var and defs, when you assign them
  //    or when you pass them


}
