package com.theory.types.witnesses

import cats.instances.tuple

/**
  *
  * <:< is a class not an operand. Is a Function1 and you're trying to create 
  * a Function1[-From,+To] => so contravariance and covariance will check if
  * possible. 
  * 
  * It will try to find an instance of it and there's just one `implicit def $conforms[A]: A <:< A` 
  * In Predef. 
  * 
  * Because the contravariance/covariance 
  *     A must be a supertype of From
  *     A must be a subtype To
  * 
  *     Therefore := From <: A <: To 
  *                 
  * 
  * MORE exactly, the mechanics of this is actually done with the conform aboves => 
  *     that points to =:= that will use the apply of singleton.asInstanceOf[A =:= A]
  *      that will use override def apply(x: Any) = x
  *     
  *      that is equivalent to:  
  *     
  *         val f: Banana ⇒ Fruit  = identity // ok  -- as per Function1[-T,+R]
  *         val f: Fruit  ⇒ Banana = identity // not ok
  *         
  *      and the prove is:
  * 
  *         val f: Banana ⇒ Fruit  = $conforms // ok
  *         val f: Fruit  ⇒ Banana = $conforms // not ok
  *     
  * 
  * =:= is the invariant version of this. 
  *
  *
  *  @implicitNotFound(msg = "Cannot prove that ${From} <:< ${To}.")
    sealed abstract class <:<[-From, +To] extends (From ⇒ To) with Serializable

    private[this] final val singleton_<:< = new <:<[Any, Any] {
        def apply(x: Any): Any = x
       }

      implicit def $conforms[A]: A => A = <:<.refl


    https://github.com/scala/scala/blob/v2.13.2/src/library/scala/typeConstraints.scala

    or click on <:< below to go to the class

  */


object Witness1 {

  //Could you force to be a subtype of one another ?
  def tupleIfSubtype[T <: U, U](t: T, u: U) = (t, u)
List(1) :+ 1
  tupleIfSubtype(42, "truky")

  def tupleIfSubtype2[T, U](t: T, u: U)(implicit ev: T <:< U) = (t, u)

  //tupleIfSubtype2(42,"truky")

}

/**     Another side of it: 
  *     can be USED to prove theorems:
        curry-howards-lambert isomorphism => 
            types as propositions, 
            if a type is habited <=> is proved <=>  can create instances <=>  a true proposition

            TYPE: sealed abstract class <:<[-From, +To] extends (From ⇒ To) with Serializable

            PROOF:  private[this] final val singleton_<:< = new <:<[Any, Any] {
                        def apply(x: Any): Any = x
                    }

  */
object WitnessOption {
  implicit class OptionOption[T](val oo: Option[Option[T]]) extends AnyVal {
    def flattenMe: Option[T] = oo.flatMap(identity)
  }

  val res: Option[String] = Some(Some("value")).flattenMe
}
