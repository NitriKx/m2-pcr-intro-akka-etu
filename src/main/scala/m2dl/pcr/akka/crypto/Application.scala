package m2dl.pcr.akka.crypto

import akka.actor.{ActorSystem, Props}
import m2dl.pcr.akka.crypto.actors.{Client, CryptoProvider, ErrorControlProvider}

/**
  * Created by Abel on 26/05/16.
  */
object Application {

  def main(args: Array[String]): Unit = {

    val actorSystem = ActorSystem("encryptAndControlSystem")

    val cryptoProvider = actorSystem.actorOf(Props(classOf[CryptoProvider]), "cryptoProvider")
    val errorController = actorSystem.actorOf(Props(classOf[ErrorControlProvider]), "errorControlProvider")
    val client = actorSystem.actorOf(Props(classOf[Client]), "requestClient")

    cryptoProvider ! CryptoProvider.CryptoOnlyRequest("test message", client)
    cryptoProvider ! ErrorControlProvider.ErrorControlRequest("error control test", client, isEncrypted = false)
    cryptoProvider ! CryptoProvider.CryptoAndControl("complexe message", errorController, client)

    Thread.sleep(10000)
    actorSystem.terminate()
  }

}
