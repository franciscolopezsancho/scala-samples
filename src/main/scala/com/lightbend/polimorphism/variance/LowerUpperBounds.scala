package com.lightbend.polimorphism.variance


/**
 * How to widen a Box? How to make a bigger Box?
 */
object LowerUpperBounds extends App {

  class Animal
  class Bird extends Animal
  class Elephant extends Animal

  class Queue[-T](content: List[T] = List.empty) {

    def enqueue(elem: T): Queue[T] =
      new Queue(elem +: content)
  }


  val a = new Queue[Bird]
  a.enqueue(new Bird).isInstanceOf[Queue[Bird]]

  // the problem
  // a.enqueue(new Elephant()).isInstanceOf[Queue[Bird]]


  //widenable Queue
  class WiderQueue[T](content: List[T] = List.empty) {

    def enqueue[B >: T](elem: B): WiderQueue[B] =
      new WiderQueue(elem +: content)
  }

  val w = new WiderQueue[Bird]
  w.enqueue(new Bird).isInstanceOf[WiderQueue[Bird]]
  w.enqueue(new Elephant()).isInstanceOf[WiderQueue[Animal]]


}
