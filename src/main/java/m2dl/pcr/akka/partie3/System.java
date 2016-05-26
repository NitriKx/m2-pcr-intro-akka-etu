package m2dl.pcr.akka.partie3;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.PoisonPill;
import akka.actor.Props;
import m2dl.pcr.akka.julien_partie2.CribleActor;
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



        // Cas d'utilisation 1



        // Cas d'utilisation 2


        // Cas d'utilisation 3

        log.debug("Actor System Shutdown Starting...");
        actorSystem.terminate();
    }
}
