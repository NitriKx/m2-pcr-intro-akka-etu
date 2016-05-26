package m2dl.pcr.akka.Part2

import java.util.concurrent.TimeUnit

import akka.actor.{ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout
import m2dl.pcr.akka.Part2.actors.NodeActor

import scala.concurrent.duration.FiniteDuration

/**
  * Created by Abel on 26/05/16.
  */
object Application {

  def main(args: Array[String]): Unit = {

    val system = ActorSystem("Eratosthene_Crible")
    val mainNode = system.actorOf(Props(classOf[NodeActor], 3), "mainNode")

    for(k <- 3 until 50) {
      mainNode ! NodeActor.NextNumber(k)
    }

    import system.dispatcher
    implicit val timeout = Timeout(FiniteDuration(30, TimeUnit.SECONDS))
    mainNode.ask(NodeActor.Stop).mapTo[NodeActor.StopConfirm].onComplete {
      case _ => system.terminate()
    }
  }
}
