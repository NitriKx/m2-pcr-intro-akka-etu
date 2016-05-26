package m2dl.pcr.akka.elliot.stringservices;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * Created by julien on 26/05/16.
 */
public class ErreurControlProviderActor extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), ErreurControlProviderActor.class);

    public ErreurControlProviderActor() {
    }

    @Override
    public void onReceive(Object msg) throws Exception {
        // Cas d'utilisation 2
        if (msg instanceof m2dl.pcr.akka.elliot.stringservices.Message) {
            m2dl.pcr.akka.elliot.stringservices.Message msgMessage = (m2dl.pcr.akka.elliot.stringservices.Message)msg;
            msgMessage.getRecepteur().tell(StringUtils.verifieCtrl(msgMessage.getMessage()),getSelf());
        }
        // Cas d'utilisation 3
        if (msg instanceof String) {
            String msgString = (String)msg;
            sender().tell(StringUtils.verifieCtrl(msgString), getSelf());
        }
    }
}
