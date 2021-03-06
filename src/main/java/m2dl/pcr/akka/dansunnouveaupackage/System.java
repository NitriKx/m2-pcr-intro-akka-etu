package m2dl.pcr.akka.dansunnouveaupackage;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import m2dl.pcr.akka.helloworld3.HelloGoodbyeActor;
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

        final ActorRef actorRef = actorSystem.actorOf(Props.create(HelloGoodbyeRouterActor.class), "hello-goodbye-router-actor");

        actorRef.tell("Lenine",null);
        actorRef.tell("Staline",null);
        actorRef.tell("Hilter",null);
        actorRef.tell("Musolini",null);

        Thread.sleep(1000);

        log.debug("Actor System Shutdown Starting...");

        actorSystem.terminate();
    }

}
