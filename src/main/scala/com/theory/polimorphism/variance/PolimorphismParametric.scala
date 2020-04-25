package com.theory.variance

import scala.reflect.ClassManifest
import scala.reflect.ClassTag
import java.sql.Date

/**
 * There are two fundamental level at which variance can be seen"
 * 
 *  1. In combination with the Liskov principle. When subtitution has place
 *  2. In itself when defininig a Polimorphic Paremtrized template, where some limitations must be respected
 *
 * goto ..implicits.parameters.typeclasses._
 * 
 * Variance/Covariance/Contravariance can be seen as the subtyping relation between continers
 * depending on the subtyping relation of it's contents. And there's is when Liskov principle kicks in.
 *
 * 
 * But if there's no subtyping (like Haskell)
 * 
 * having a Container[A] that you want to use as a Container[B] is let say A -> B. A = Bird and B = Animal
 * 
 * in scala{
 *  val cont: Container[Animal] = new Container[Bird]
 * }
 * 
 *  Container is covariant if you need a function such as A => B to allow such behavior (same direction) 
 *  Contra is you need a function B => A (opposite direction)
 *  Invariant if you both (both)
 * 
 * 
 * Scala will provide such a function, implicitly, when there's subtyping relation
 *  for A => B it will provide a witness A <:< B
 * 
 * 
 * NOT SOLVED. CAN'T PROVE WHY it must contravariant type in covariant position and viceversa
 * 
 */
object PolimorphismParametric {

  class Animal
  class Bird extends Animal


  //invariance
  class BoxInv[T]

  val inv: BoxInv[Animal] = new BoxInv[Animal]

  //covariance
  class BoxCov[+T]
  //liskov substitution of subtype
  val cov: BoxCov[Animal] = new BoxCov[Bird]()

  // COVARIANT is the default in the OUTPUT 
  val f0: scala.Function0[Animal] = () => new Bird
  // same as above, it's a getter
  class Container[+A](content: A)
  // but bare in mind you can override it BUT as long as you specify a type
  case class ContravariantContainer[A >: Bird](content: A)
  // so no contravariant MyFunction0 can't be implemented


  //contravariance
  class BoxContr[-T]

  //liskov substitution inverted, therefore supertype
  val contr: BoxContr[Bird] = new BoxContr[Animal]()

  // CONTRAVARIANT is the default in the INPUT
  val f1: scala.Function1[Bird,Number] = (bird: Animal) => 2



  // if INVARIANT then there's no default VARIANCE as there's no subtype relation. 
  // it can be just the choosen type when created the container.
  abstract class Cage[A,B](val content: A) {
    def getter: A  
    def transform(a: A,f: A => B): B 
    val animalLLL: A 
    var animalRRR: A 
  }


  


}


/** FURTHER THINKING
 * 
 * I  think now the solution is in Functors ()
 *    import cats.Functor
      import scala.Option
 * https://ocharles.org.uk/blog/guest-posts/2013-12-21-24-days-of-hackage-contravariant.html
 * 
 *  *  Container is covariant if you need a function such as A => B to allow such behavior (same direction) ( @see 54)
 *        If you want to use a function that produces Birds in place of a function that produces Animal
 *        and you are willing to provide a function from  Bird to Animal (seems reasonable) you're good to go
 * 
 *         If you want to use a function that consume Birds in place of a function that consume Animals
 *          then you'll need the same function. 
 *           
 *  Contra is you need a function B => A (opposite direction)
 *       
 *  Invariant if you both (both)
 * 
 * 
 * So the key here I guess it has to do with functions. A function can be inyective:
 * From set A to set B. a1 and a2 can be mapped to b1. 
 * But a1 can't be mapped to b1 and b2. 
 * 
 *    sample: 
 *  Covariance From Container[Animal] to Dog
 * 
 * */
