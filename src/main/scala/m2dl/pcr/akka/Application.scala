package m2dl.pcr.akka

import java.util.concurrent.TimeUnit

import akka.actor.{ActorSystem, Props}
import m2dl.pcr.akka.actors.NodeActor
import akka.pattern.ask
import akka.util.Timeout

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
