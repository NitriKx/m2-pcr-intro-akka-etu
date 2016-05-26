package m2dl.pcr.akka.partie4;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import m2dl.pcr.akka.partie3.CryptageProviderActor;

/**
 * Created by benoit on 26/05/2016.
 */
public class CryptageProviderVMEntryPoint {

    public static void main(String[] args) {

        Config config = ConfigFactory.load();
        final ActorSystem actorSystem = ActorSystem.create("cryptage-system", config.getConfig("cryptoservice").withFallback(config));

        final ActorRef cryptage = actorSystem.actorOf(Props.create(CryptageProviderActor.class), "cryptage");
    }
}
