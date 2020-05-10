package com.theory.types.typeerasure

import scala.reflect.runtime.universe.{TypeTag, typeOf}


/**
  * When working with Generict
  */
object TypeErasure extends App {


  /**
    * Will never get to List[Int], see at the end of the file 
    *
    * @param list
    */
  List(1, 2) match {
    case b: List[String] => println("list of string")
    case a: List[Int] => println ("list of int")
  }

  /**
    * This has to be done though reflection
    * This notation (the implicit) forces to the compiler to generate a TypeTag t
    *   that will store the type
    *   
    * [A : TypeTag] is equivalent to `implicit tag: TypeTag[A]`
    * 
    * 
    * Then to retreive the type of A, also 
    * @param list
    */
  def myMatcher[A : TypeTag](list: List[A]): Unit = {
    typeOf[A] match {
      case t if t =:= typeOf[String] =>  println("list of string")
      case t if t =:= typeOf[Int] =>  println("list of int")
    }
  }

  myMatcher(List(1,2))

}


/**
 * 
 * public final void delayedEndpoint$com$theory$types$typeerasure$TypeErasure$1() {
        List list = (List)List$.MODULE$.apply((Seq)ScalaRunTime$.MODULE$.wrapIntArray((int[])new int[]{1, 2}));
        if (list instanceof List) {
            Predef$.MODULE$.println((Object)"list of string");
            BoxedUnit boxedUnit = BoxedUnit.UNIT;
        } else if (list != null) {
            Predef$.MODULE$.println((Object)"list of int");
            BoxedUnit boxedUnit = BoxedUnit.UNIT;
        } else {
            throw new MatchError((Object)list);
        }
        this.myMatcher((List)List$.MODULE$.apply((Seq)ScalaRunTime$.MODULE$.wrapIntArray((int[])new int[]{1, 2})), ((TypeTags)package$.MODULE$.universe()).TypeTag().Int());
    }

 */
