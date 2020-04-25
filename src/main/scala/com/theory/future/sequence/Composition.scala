package com.theory.future.sequence

import scala.concurrent.Future
import scala.concurrent.ExecutionContext
import java.util.concurrent._

object Composition extends App {


  implicit val ec =
    ExecutionContext.fromExecutor(Executors.newFixedThreadPool(3))

  type A
  type B
  type C

  def f1(x: A): Future[Seq[B]] = ???
  def f2(x: B): Future[Option[C]] = ???

  

  def composed(a: A)(implicit ec: ExecutionContext): Future[Seq[Option[C]]] =
    for {
      bs <- f1(a)
      cOpts <- Future.traverse(bs)(f2)
    } yield cOpts

  def composed2(a: A)(implicit ec: ExecutionContext): Future[Seq[C]] =
    for {
      bs <- f1(a)
      cOpts <- Future.traverse(bs)(f2)
    } yield cOpts.flatten

  def composed3(a: A)(implicit ec: ExecutionContext): Future[Seq[(C,B)]] =
    for {
      bs <- f1(a)
      ts <- Future.traverse(bs)(b => f2(b).map(_.map(_ -> b)))
    } yield ts.flatten

}
