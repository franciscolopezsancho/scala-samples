package com.theory.types.selftypes.composition

import akka.actor.{Actor, ActorContext, ActorLogging, ActorSystem, Props}
import com.theory.types.selftypes.composition.CabinActor.Greeting


/**
 * A self type will mainly allow us to add a trait as a plugin.
 *
 * The idea is add this functionality to a class not AS a class,
 * this funtionality doens't have meaning by itself. By definition.
 */

object CompositionAsAPlugin extends App  {

  val actorSystem = ActorSystem("Bencho")
  val actorRef = actorSystem.actorOf(CabinActor.props)

  actorRef ! CabinActor.Greeting("hello")



}
// This will not work with ActorRef.
// Shall I create simple example with an Animal class?
trait ActorHandler1 {self: Actor  =>
  override def preStart(): Unit = println(s"Actor ${context.self.path.name} starting .. with Handler ")
  def children(context: ActorContext) = context.children.toList


}

class CabinActor extends Actor with ActorHandler1 {

  def receive  =  {
    case Greeting(message) =>
      println(s"got this message $message")
  }
}

object CabinActor {

  def props: Props = Props[CabinActor]

  final case class Greeting(greeting: String)

}


/**
 * This will not work if added to ActorHandler1 as he already has that method
 * i.e.
 * class ActorWithManyHandlers extends Actor with ActorHandler1 with ActorHandler2 {
 *   override def receive: Receive = ???
 * }
 */
trait ActorHandler2 {self: Actor  =>
  override def preStart(): Unit = println(s"Actor ${context.self.path.name} starting .. ")
  def children(context: ActorContext) = context.children.toList
}
//TODO ? how to add multiple handles and compose a more complex preStart method.


