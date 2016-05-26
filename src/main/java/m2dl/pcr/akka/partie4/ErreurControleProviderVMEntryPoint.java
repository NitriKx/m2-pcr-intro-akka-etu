package m2dl.pcr.akka.partie4;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import m2dl.pcr.akka.partie3.CryptageProviderActor;
import m2dl.pcr.akka.partie3.ErreurControlProviderActor;

/**
 * Created by benoit on 26/05/2016.
 */
public class ErreurControleProviderVMEntryPoint {

    public static void main(String[] args) {

        Config config = ConfigFactory.load();
        final ActorSystem actorSystem = ActorSystem.create("erreurservice-system", config.getConfig("erreurservice").withFallback(config));

        final ActorRef erreurControle = actorSystem.actorOf(Props.create(ErreurControlProviderActor.class), "erreurcontrole");

    }
}
