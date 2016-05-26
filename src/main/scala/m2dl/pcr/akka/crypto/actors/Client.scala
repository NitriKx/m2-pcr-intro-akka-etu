package m2dl.pcr.akka.crypto.actors

import akka.actor.{Actor, ActorLogging}
import m2dl.pcr.akka.stringservices.StringUtils

/**
  * Created by Abel on 26/05/16.
  */
class Client extends Actor with ActorLogging {

  override def receive: Receive = {
    case CryptoProvider.EncryptedMessage(msg) =>
      log.debug("received encrypted message [{}] - decrypted [{}]", msg, StringUtils.decrypte(msg))

    case ErrorControlProvider.ErrorControlResponse(msg, isEncrypted) if isEncrypted =>
      log.debug("received controlled and encrypted message [{}] - decrypted [{}]", msg, StringUtils.decrypte(StringUtils.verifieCtrl(msg)))

    case ErrorControlProvider.ErrorControlResponse(msg, isEncrypted) =>
      log.debug("received controlled message [{}]", msg)
  }
}
