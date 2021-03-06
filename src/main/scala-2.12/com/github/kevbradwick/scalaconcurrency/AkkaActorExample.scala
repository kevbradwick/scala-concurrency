package com.github.kevbradwick.scalaconcurrency

import akka.actor.{Actor, ActorSystem, Props}

class HelloActor extends Actor {
  override def receive: Actor.Receive = {
    case "hello" => println("Hello World")
    case n: Int => println(s"received a number: $n")
    case _ => println("???")
  }
}

object AkkaActorExample extends App {

  val system = ActorSystem("Hello-System")
  val helloActor = system.actorOf(Props[HelloActor], name = "helloactor")

  helloActor ! "hello"
  helloActor ! "foo"
  helloActor ! 123

}
