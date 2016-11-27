package com.github.kevbradwick.scalaconcurrency

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success}
import scala.language.postfixOps


/**
  * The simplest case, creating one Future and blocking the main thread to wait for the code
  * to finish.
  *
  * sbt "run-main com.github.kevbradwick.scalaconcurrency.FutureExample"
  */
object FutureExample extends App with Helper{

  // create a new future. the block is the code that will be run asynchronously.
  val future: Future[Int] = Future {
    sleep(1000)
    12
  }

  future onComplete {
    case Success(value) => println(s"Callback with success: $value")
    case Failure(e) => println(s"Future failed. ${e.getMessage}")
  }

  // block the main thread to wait for the futures to complete.
  // the first one will timeout after 2 seconds. you'll see the callback print the success message.
  // with the second, it will timeout after half a second and you'll get a stack trace in the console.
  //
  Await.result(future, 2 seconds)
//  Await.ready(future, 500 millis)
}
