package m2dl.pcr.akka.eratosphene;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Leo on 26/05/16.
 */
public class System {

    public static final Logger log = LoggerFactory.getLogger(System.class);


    public static void main(String... args) throws Exception {

        final ActorSystem actorSystem = ActorSystem.create("actor-system");

        Thread.sleep(5000);

        final ActorRef actorRef = actorSystem.actorOf(Props.create(ErastospeneTesterActor.class, 3), "hello-goodbye-router-actor");
        for (int i = 3; i < Integer.MAX_VALUE; i++) {
            actorRef.tell(i, null);
        }
        actorRef.tell("quit",null);

        Thread.sleep(1000);

        log.debug("Actor System Shutdown Starting...");

        actorSystem.terminate();
    }

}
