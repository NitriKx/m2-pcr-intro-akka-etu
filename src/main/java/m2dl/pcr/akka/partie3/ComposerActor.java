package m2dl.pcr.akka.partie3;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import m2dl.pcr.akka.stringservices.StringUtils;

/**
 * Created by julien on 26/05/16.
 */
public class ComposerActor extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), ErreurControlProviderActor.class);

    public ComposerActor() {
    }


    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg instanceof Message) {
            Message message = (Message) msg;
            message.getCrypteur().tell(msg, getSelf());

        } else {
            unhandled(msg);
        }
    }
}
