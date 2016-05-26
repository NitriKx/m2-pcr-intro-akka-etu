package m2dl.pcr.akka.crypto.actors

import akka.actor.{Actor, ActorLogging, ActorRef}
import m2dl.pcr.akka.crypto.actors.CryptoProvider.EncryptedMessage
import m2dl.pcr.akka.stringservices.StringUtils

/**
  * Created by Abel on 26/05/16.
  */
object CryptoProvider {
  case class CryptoOnlyRequest(msg: String, client: ActorRef)
  case class CryptoAndControl(msg: String, errorController: ActorRef, client: ActorRef)
  case class EncryptedMessage(msg: String)
}

class CryptoProvider extends Actor with ActorLogging {

  override def preStart() = {
    super.preStart()
    log.debug("starting cryptoProvider actor")
  }

  override def postStop() = {
    log.debug("stopped cryptoProvider actor")
    super.postStop()
  }

  override def receive: Receive = {
    case CryptoProvider.CryptoOnlyRequest(msg, client) =>
      log.debug("received request - encrypting [msg {}]", msg)
      val encryptedMsg = StringUtils.crypte(msg)
      log.debug("sending encrypted message to [client {}]", client)
      client ! EncryptedMessage(encryptedMsg)

    case CryptoProvider.CryptoAndControl(msg, errorController, client) =>
      log.debug("received request - encrypting [msg {}]", msg)
      val encryptedMsg = StringUtils.crypte(msg)
      log.debug("sending encrypted message to [error controller {}]", errorController)
      errorController ! ErrorControlProvider.ErrorControlRequest(encryptedMsg, client, isEncrypted = true)

  }
}
