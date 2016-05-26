package m2dl.pcr.akka.elliot.stringservices;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * Created by julien on 26/05/16.
 */
public class CryptageProviderActor extends UntypedActor {
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public CryptageProviderActor() {
        log.info("CryptageProviderActor constructor");
    }

    @Override
    public void onReceive(Object msg) throws Exception {
        // Cas d'utilisation 1
        if (msg instanceof Message) {
            Message msgMessage = (Message)msg;
            msgMessage.getRecepteur().tell(StringUtils.crypte(msgMessage.getMessage()), getSelf());
        }
        // Cas d'utilisation 3
        if (msg instanceof String) {
            String msgString = (String)msg;
            sender().tell(StringUtils.crypte(msgString), getSelf());
        }
    }
}
