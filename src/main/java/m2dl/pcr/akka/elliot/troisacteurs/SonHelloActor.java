package m2dl.pcr.akka.elliot.troisacteurs;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * Created by Elliot on 26/05/2016.
 */
public class SonHelloActor extends UntypedActor {
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public SonHelloActor() {
        log.info("HelloActor constructor");
    }

    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg instanceof String) {
            log.info("Hello, " + msg);
        } else {
            unhandled(msg);
        }
    }
}