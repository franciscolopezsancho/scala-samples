package com.theory.category

import cats._
import cats.implicits._


/**
 * https://books.google.com.co/books?id=dG32BwAAQBAJ&pg=PA383&lpg=PA383&dq=polarity+function+contravariance&source=bl&ots=jpkXQ3e80o&sig=ACfU3U2-XNPnzIiCs7lkAFMQDzRPCzqfqQ&hl=en&sa=X&ved=2ahUKEwju_cqr0__oAhWRTN8KHU-VDmQQ6AEwAnoECAgQAQ#v=onepage&q=polarity%20function%20contravariance&f=false
  * https://typelevel.org/cats/typeclasses/contravariant.html
  */
object ContravariantFunctor extends App {

    case class Money(amount: Int)
    case class Salary(size: Money)

    implicit val showMoney: Show[Money] = Show.show(m => s"$${m.amount}")

    implicit val showSalary: Show[Salary] = showMoney.contramap(_.size)





    class Animal(){
        def name: String = "im an animal"
    }
    class Bird extends Animal {
        override def name: String = "Im a bird"
    }

    implicit val showAnimal: Show[Animal] = Show.show(a => s"Hi ${a.name}")

    implicit val showBird: Show[Bird] = showAnimal.contramap(b => b: Animal)

    // don't forget it's using show Animal
    println(new Bird().show)







}