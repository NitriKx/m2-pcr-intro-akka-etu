package m2dl.pcr.akka.elliot.stringservices;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Main runtime class.
 */
public class System {

    public static final Logger log = LoggerFactory.getLogger(System.class);

    public static void main(String... args) throws Exception {

        final ActorSystem actorSystem = ActorSystem.create("actor-system");

        Thread.sleep(5000);

        final ActorRef cryptage = actorSystem.actorOf(Props.create(CryptageProviderActor.class), "Cryptage");
        final ActorRef erreurControle = actorSystem.actorOf(Props.create(ErreurControlProviderActor.class), "ErreurControle");
        final ActorRef recepteur = actorSystem.actorOf(Props.create(RecepteurActor.class), "Recepteur");
        final ActorRef composerActor = actorSystem.actorOf(Props.create(ComposerActor.class, cryptage, erreurControle), "Composer");

        String str = "Une chaine a crypter cas d'utilisation 1";
        m2dl.pcr.akka.elliot.stringservices.Message message = new Message(recepteur, str);

        // Cas d'utilisation 1
        log.info("CAS D'UTILISATION N° 1");
        cryptage.tell(message, null);

        // Cas d'utilisation 2
        log.info("CAS D'UTILISATION N° 2");
        erreurControle.tell(message, null);

        // Cas d'utilisation 3
        log.info("CAS D'UTILISATION N° 3");
        composerActor.tell(message, null);

        log.debug("Actor System Shutdown Starting...");
        actorSystem.terminate();
    }
}
