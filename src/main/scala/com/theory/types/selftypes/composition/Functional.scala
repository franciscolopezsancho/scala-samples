package com.theory.types.selftypes.composition

import akka.actor.{Actor, ActorContext}

object BenchComposition extends App  {

  //new CabinActor().preStart()

}

trait ActorHandler {
  def preStart(context: ActorContext) = s"Actor ${context.self.path.name} starting .. "
  def children(context: ActorContext) = context.children.toList


}
// this is a different thing you want be ab
class FamousActor extends Actor  {
  val handler = new Object with ActorHandler

  override def preStart() = handler.preStart(this.context)
    // is less obvious where context comes from
        // also prone error

  override def receive: Receive = ???
}
