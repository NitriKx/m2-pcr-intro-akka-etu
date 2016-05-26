package m2dl.pcr.akka.Part2.actors

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import m2dl.pcr.akka.Part2.actors.NodeActor.StopConfirm

/**
  * Created by Abel on 26/05/16.
  */
object NodeActor {
  case class NextNumber(number: Int)
  case object Stop
  case class StopConfirm(childActor: ActorRef)
}

class NodeActor(base: Int) extends Actor with ActorLogging {

  override def preStart() = {
    log.debug(s"[base: $base] Starting node actor")
    super.preStart()
  }

  override def postStop() = {
    super.postStop()
    log.debug(s"[base: $base] stopped node actor")
  }

  override def receive: Receive = stopHandler(None) orElse {
    case NodeActor.NextNumber(number) if number % base == 0 =>
      log.debug(s"[base: $base] received multiple {}", number)

    case m@NodeActor.NextNumber(number) =>
      log.debug(s"[base: $base] received unhandled number - creating additional node")
      val nextActor = context.actorOf(Props(classOf[NodeActor], number), s"NodeActor_$number")
      nextActor ! m
      context.become(filteringMoreNumbers(nextActor))
  }

  private def waitingStopConfirmation(nextActor: ActorRef): Receive = {
    case StopConfirm(`nextActor`) =>
      log.debug("received next actor stop confirmation - stopping")
      confirmAndStop
  }

  private def stopHandler(nextActor: Option[ActorRef]): Receive = {
    case m@NodeActor.Stop =>
      if (nextActor.isDefined) {
        nextActor.get ! m
        waitingStopConfirmation(nextActor.get)
      } else {
        confirmAndStop
      }
  }

  private def filteringMoreNumbers(nextActor: ActorRef): Receive = stopHandler(Some(nextActor)) orElse {
    case NodeActor.NextNumber(number) if number % base == 0 =>
      log.debug(s"[base: $base] received multiple {}", number)

    case m@NodeActor.NextNumber(number) =>
      log.debug(s"[base: $base] received unhandled number {} - forwarding to next actor", number)
      nextActor ! m
  }

  def confirmAndStop: Unit = {
    sender() ! NodeActor.StopConfirm(self)
    context.stop(self)
  }
}
