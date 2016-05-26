package m2dl.pcr.akka.partie3;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import m2dl.pcr.akka.stringservices.StringUtils;

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
        if (msg instanceof Message) {
            Message msgMessage = (Message) msg;
            String messageCrypte = StringUtils.crypte(((Message) msg).getMessage());
            ((Message) msg).setMessage(messageCrypte);
            msgMessage.getRecepteur().tell(msg, getSelf());
        }
    }
}
