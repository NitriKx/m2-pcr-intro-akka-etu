package m2dl.pcr.akka.elliot.troisacteurs;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Elliot on 26/05/2016.
 */
public class System {
    public static final Logger log = LoggerFactory.getLogger(System.class);

    public static void main(String... args) throws Exception {

        final ActorSystem actorSystem = ActorSystem.create("actor-system");

        Thread.sleep(5000);

        final ActorRef actorRef = actorSystem.actorOf(Props.create(ParentActor.class), "parent-actor");

        actorRef.tell("Sarkozy > Valls :)",null);
        actorRef.tell("49.3 is the new Colt",null);
        actorRef.tell("YA KELKUN ?",null);
        actorRef.tell("pas de pétrole pour les trolls :/",null);

        Thread.sleep(1000);

        log.debug("Actor System Shutdown Starting...");

        actorSystem.terminate();
    }
}
