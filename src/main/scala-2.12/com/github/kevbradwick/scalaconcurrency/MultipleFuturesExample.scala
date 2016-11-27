package com.github.kevbradwick.scalaconcurrency

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success}
import scala.language.postfixOps
import scala.concurrent.duration._


/**
  * An example of composing a sequence of Futures.
  *
  * sbt "run-main com.github.kevbradwick.scalaconcurrency.MultipleFuturesExample"
  */
object MultipleFuturesExample extends App with Helper {

  // create 3 futures, all taking 1sec to complete and return its number. there is no guarantee of the order that
  // each one will execute.
  val f1: Future[Int] = Future { sleep(1000); 1 }
  val f2: Future[Int] = Future { sleep(1000); 2 }
  val f3: Future[Int] = Future { sleep(1000); 3 }

  // compose a sequence of futures and define the onComplete callback of each
  val allFutures = List(f1, f2, f3).map { f =>
    f.onComplete({
      case Success(number) => println(s"Future $number complete!")
      case Failure(error) => println(s"Future failed: ${error.getMessage}")
    })
    f
  }

  val all: Future[List[Int]] = Future.sequence(allFutures)

  Await.result(all, 1 seconds)
}
