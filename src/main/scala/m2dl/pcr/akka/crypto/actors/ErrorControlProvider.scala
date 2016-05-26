package m2dl.pcr.akka.crypto.actors

import akka.actor.{Actor, ActorLogging, ActorRef}
import m2dl.pcr.akka.stringservices.StringUtils

/**
  * Created by Abel on 26/05/16.
  */
object ErrorControlProvider {
  case class ErrorControlRequest(msg: String, client: ActorRef, isEncrypted: Boolean)
  case class ErrorControlResponse(msg: String, isEncrypted: Boolean)
}

class ErrorControlProvider extends Actor with ActorLogging {

  override def receive: Receive = {
    case ErrorControlProvider.ErrorControlRequest(msg, client, isEncrypted) =>
      log.debug("received error control request [msg {}]", msg)
      val controlledMsg = StringUtils.ajouteCtrl(msg)
      log.debug("sending controlled message to [client {}]", client)
      client ! ErrorControlProvider.ErrorControlResponse(controlledMsg, isEncrypted)
  }
}
