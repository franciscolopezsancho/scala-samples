//package com.lightbend.future.paralelism
//
//import java.util.concurrent.Executors
//
//import akka.actor.typed.scaladsl.Behaviors
//import akka.actor.typed.{ActorRef, Behavior}
//import com.lightbend.future.paralelism.Breakfast.Helper
//
//import scala.concurrent.{ExecutionContext, Future}
//import scala.util.{Failure, Success}
//object Human {
//
//  def apply: Behavior[Command] = {
//    Behaviors.receive { (context, message) =>
//      message match {
//        case StartBreakfast =>
//
//      )
//      }
//
//
//    }
//  }
//
//  def putJamOnToast(slice: Toast, topping: String) = Future {
//    Helper.print("toppING")
//    Thread.sleep(5000) // on avg a human being spreads jam on a toast in about 5s
//    Helper.print("toppED")
//    slice.copy(topping = topping)
//  }
//
//  def putCapsuleInNespresso(time: Long) = Future {
//    Helper.print("preparING coffee")
//    Thread.sleep(time)
//    Helper.print("preparED coffee")
//    Coffee(water = time)
//  }
//
//  def putMilkOnCoffee(coffee: Coffee, milk: Long) = Future {
//    Helper.print("pourING milk")
//    Thread.sleep(milk)
//    Helper.print("pourED milk")
//    coffee.copy(milk = milk)
//  }
//
//  implicit val ec = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(8))
//
//  sealed trait Command
//
//  case class Toast(topping: String = "", toastedTime: Long) extends
//
//  case class Coffee(water: Long, milk: Long = 0) extends
//
//  case class Toast(topping: String = "", toastedTime: Long)
//
//  case class Coffee(water: Long, milk: Long = 0)
//
//  case object StartBreakfast extends Command
//
//  case class Toasted(toast: Toast) extends Command
//
//
//}
//
//object Toaster {
//  implicit val ec = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(8))
//
//
//  def apply: Behavior[Command] = {
//    Behaviors.receiveMessage {
//      case ToastIt(replyTo, time, toToast) =>
//        putToastInToaster(time,toToast).onComplete {
//          case Success(toast) => replyTo ! toast
//          case Failure(exception) => replyTo ! Burned
//        }
//        Behaviors.same
//
//
//    }
//
//  }
//
//  def putToastInToaster(time: Int, toast: Toast) = Future {
//    Helper.print("toastING")
//    Thread.sleep(time)
//    Helper.print("toastED")
//    toast.copy(toastedTime = time)
//  }
//
//  implicit val ec = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(8))
//
//  sealed trait Command
//
//  case class ToastIt(replyTo: ActorRef[Human.Command], time: Int, toToast: Toast) extends Command
//
//  case class Toast(topping: String = "", toastedTime: Long)
//
//  case object Burned extends Command
//
//
//}
//
//
