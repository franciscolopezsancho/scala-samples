package com.theory.category

import scala.Either
import scala.Left
import cats.Functor

/**
  * https://www.youtube.com/watch?v=JZPXzJ5tp9w
  */

object FunctorsBench extends App {

//   trait Functor[F[_]] {
//     def map[A, B](fa: F[A])(f: A => B): F[B]
//   }

//   class Functor[Either[_,_]] {
//     def fmap[A, B, C](f: B => C)(e: Either[A, B]): Either[A, C] = {
//       e match {
//         case l @ Left => l
//         case Right(x) => Right(f(x))
//       }
//     }
//   }

}

