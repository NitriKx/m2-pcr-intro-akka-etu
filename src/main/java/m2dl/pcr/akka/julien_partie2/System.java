package m2dl.pcr.akka.julien_partie2;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.PoisonPill;
import akka.actor.Props;
import m2dl.pcr.akka.julien_exercice1point2.ParentActor;
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

        final ActorRef actorRef = actorSystem.actorOf(Props.create(CribleActor.class, 2), "crible-2");

        int i = 2;

        while(i < 100) {
            actorRef.tell(i ,null);
            i++;
            Thread.sleep(100);
        }

        log.info("Envoi de stop en chaine");
        actorRef.tell("KILL" ,null);


        log.debug("Actor System Shutdown Starting...");
        actorRef.tell(PoisonPill.getInstance(), null);
        actorSystem.terminate();
    }
}
