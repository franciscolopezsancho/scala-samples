package com.lightbend.selftypes.composition

import akka.actor.{Actor, ActorContext, ActorLogging, ActorSystem, Props}

object Bencho extends App  {

  val actorSystem = ActorSystem("Bencho")

  val actorRef = actorSystem.actorOf(Props[ActorWithManyHandlers])

  actorRef ! "hello"



}

trait ActorHandler1 {self: Actor  =>
  override def preStart() = s"Actor ${context.self.path.name} starting .. "
  def children(context: ActorContext) = context.children.toList


}

trait ActorHandler2 {self: Actor  =>
  override def preStart(): Unit = println(s"Actor ${context.self.path.name} starting .. ")
  def children(context: ActorContext) = context.children.toList
}

class ActorWithManyHandlers extends Actor with ActorHandler1 with ActorHandler2 {
  override def receive: Receive = ???
}
// this is a different thing you want be able to add multiple
// also above is a plug-in to a class below is the class itself
class CabinActor extends Actor {

  override def preStart(): Unit = println(s"Actor ${context.self.path.name} starting .. ")

  override def receive: Receive = ???
}
