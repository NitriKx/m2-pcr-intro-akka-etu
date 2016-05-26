package m2dl.pcr.akka.partie4;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.util.Timeout;
import m2dl.pcr.akka.partie3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.concurrent.Await;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by Benoît Sauvère on 26/05/2016.
 */
public class Partie4ScenarioEntryPoint {

    public static final Logger log = LoggerFactory.getLogger(Partie4ScenarioEntryPoint.class);

    public static void main(String[] args) throws Exception {

        final ActorSystem actorSystem = ActorSystem.create("partie4-system");

        Thread.sleep(5000);

        final ActorRef cryptage = Await.result(actorSystem.actorSelection("akka.tcp://app@127.0.0.1:9876/crypto").resolveOne(Timeout.apply(1, TimeUnit.SECONDS)), Duration.apply(1, TimeUnit.SECONDS));
        final ActorRef erreurControle = Await.result(actorSystem.actorSelection("akka.tcp://app@127.0.0.1:9877/erreurcontrole").resolveOne(Timeout.apply(1, TimeUnit.SECONDS)), Duration.apply(1, TimeUnit.SECONDS));

        final ActorRef recepteur = actorSystem.actorOf(Props.create(RecepteurActor.class), "Recepteur");
        final ActorRef composerActor = actorSystem.actorOf(Props.create(ComposerActor.class), "Composer");

        String str = "Une chaine a crypter cas d'utilisation 1";
        Message message = new Message(recepteur, str);

        // Cas d'utilisation 1
        log.info("CAS D'UTILISATION N° 1");
        cryptage.tell(message, null);


        // Cas d'utilisation 2
        log.info("CAS D'UTILISATION N° 1");
        message.setMessage("Une chaine a crypter cas d'utilisation 2");
        erreurControle.tell(message, null);


        // Cas d'utilisation 3
        message.setMessage("Une chaine a crypter cas d'utilisation 3");
        composerActor.tell(message, null);

        log.debug("Actor System Shutdown Starting...");
        actorSystem.terminate();
    }
}
