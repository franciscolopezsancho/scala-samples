package com.theory

import cats.Eval
import cats.data.Reader
import scala.annotation.tailrec
import concurrent._
import concurrent.duration._
import java.util.concurrent.Executors
import scala.concurrent.java8.FuturesConvertersImpl.P

object Workbench extends App {

  type DbReader[A] = Reader[Db,A]
  
  case class Db(
      usernames: Map[Int, String],
      passwords: Map[String, String]
  )
      
    
  val users = Map(
    1 -> "dade",
    2 -> "kate",
    3 -> "margo"
  )
  val passwords = Map(
    "dade" -> "zerocool",
    "kate" -> "acidburn",
    "margo" -> "secret"
  )
  val db = Db(users, passwords)

  def findUsername(userId: Int): DbReader[Option[String]] =
    Reader(db => db.usernames.get(userId))
  def checkPassword(username: String, password: String): DbReader[Boolean] =
   Reader(db => db.passwords.get(username).contains(password))
  def checkLogin(userId: Int, password: String): DbReader[Boolean] = {
   Reader(db => db.passwords.get(password).contains(users.get(userId)))
  }


  checkLogin(1, "zerocool").run(db)
// res10: cats.Id[Boolean] = true
  checkLogin(4, "davinci").run(db)
// res11: cats.Id[Boolean] = false
println("wow")

}
