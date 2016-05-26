package esrasthosthèsnnhes;

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

    private static final int MAX = 150;

    public static void main(String... args) throws Exception {

        final ActorSystem actorSystem = ActorSystem.create("actor-system");

        Thread.sleep(5000);

        final ActorRef actorRef = actorSystem.actorOf(Props.create(CribleActor.class, 2), "crible-2-actor");

        for (int i = 3; i <= MAX; i++) {
            actorRef.tell(""+i,null);
        }
        Thread.sleep(1000);

        log.debug("Actor System Shutdown Starting...");

        actorSystem.terminate();
    }
}
