package com.lightbend.variance

/**
 * Is about defining the possible IMPLEMENTATIONS but NOT about using it. @see 36
 *
 * goto ..implicits.parameters.typeclasses._
 *
 */
object PolimorphismParametric {

  class Animal
  class Bird extends Animal


  //invariance
  class BoxInv[T]

  val inv: BoxInv[Animal] = new BoxInv[Animal]

  //convariance
  class BoxCov[+T]

  val cov: BoxCov[Animal] = new BoxCov[Bird]()


  //contravariance
  class BoxContr[-T]

  val contr: BoxContr[Bird] = new BoxContr[Animal]()




  // GENERICS != AdHoc
  class Container[+T](val content: T) //remember this is a getter
  //method content behaves as Covariant?

  val b: Container[Animal] = new Container(new Bird)
  // GENERICS != AdHoc
  // this sample shows polimorphism AdHoc
  




  // How rules of Generics work on building implementations


  // COVARIANT + in OUTPUT
  val f0: scala.Function0[Animal] = () => new Bird
  // CONTRAVARIANT in the INPUT
  val f1: scala.Function1[Bird,Number] = (bird: Animal) => 2

  // if INVARIANT then all is good
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


  // variance is about the relation of COMPOSITE types, themselves, their definitions not the values we pass
  //    is about rules over the definition of the val,var,and defs under Parametric Polimorphism
  //    , no about the rules how to assign them nor use them
  //    Also Functions are COMPOSITE types
  // subtyping is about the relation of types, of the values used in val, var and defs, when you assign them
  //    or when you pass them


}
