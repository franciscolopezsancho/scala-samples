package com.lightbend.future.paralelism

import java.util.Date
import java.util.concurrent.Executors

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}


// breakfast
// prepare the toast -> toaster, then butter
// preparet coffeee -> nesspresso, then milk
// get both and put them in the table


//I need to decide what do I do next with just my self
// in case coffee comes up sooner I'll add milk but if
// not I'll put the milk

//Next level is doing sequential after part is parallel
// if I'm doing toast I can' to milk, I'll use actors
object Breakfast1 extends App {



  implicit val ec = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(4))


  val doToast =  prepareToast(10000, "Apricot")
  val doCoffee =  prepareCoffee(10000, 2000)

  val breakfast: Future[(Toast, Coffee)] = for {
    t <- doToast
    c <- doCoffee
  } yield (t, c)

  breakfast.onComplete {
    case Success(value) =>
      val (toast, coffee) = value
      println(s"enjoying $toast and $coffee")
    case Failure(exception) => println(exception)

  }

  def prepareToast(toastingTime: Long, topping: String): Future[Toast] = for {
    toasted <- putToastInToaster(toastingTime)
    ready <- putJamOnToast(toasted, topping)
  } yield ready

  def prepareCoffee(coffee: Long, milk: Long): Future[Coffee] = for {
    c <- putCapsuleInNespresso(coffee)
    ready <- putMilkOnCoffee(c, milk)
  } yield ready





  def putToastInToaster(time: Long) = Future {
    Helper.print("toastING")
    Thread.sleep(time)
    Helper.print("toastED")
    Toast(toastedTime = time)
  }

  def putJamOnToast(slice: Toast, topping: String) = Future {
    Helper.print("toppING")
    Thread.sleep(5000)// on avg a human being spreads jam on a toast in about 5s
    Helper.print("toppED")
    slice.copy(topping = topping)
  }



  def putCapsuleInNespresso(time: Long) = Future {
    Helper.print("preparING coffee")
    Thread.sleep(time)
    Helper.print("preparED coffee")
    Coffee(water = time)
  }

  def putMilkOnCoffee(coffee: Coffee, milk: Long) = Future {
    Helper.print("pourING milk")
    Thread.sleep(milk)
    Helper.print("pourED milk")
    coffee.copy(milk = milk)
  }

  case class Toast(topping: String = "", toastedTime: Long)

  case class Coffee(water: Long, milk: Long = 0)

  object Helper {
    def print(state: String): Unit = {
      println(s"$state at ${new Date().getSeconds}, thread ${Thread.currentThread.getName.toUpperCase}")
    }
    def factorial(n: Int) = (BigInt(1) to BigInt(n)).product
  }
}


