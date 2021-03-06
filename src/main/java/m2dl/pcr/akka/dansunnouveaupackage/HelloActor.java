package m2dl.pcr.akka.dansunnouveaupackage;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * Created by Leo on 26/05/16.
 */
public class HelloActor extends UntypedActor {
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public HelloActor() {
        log.info("HelloActor constructor");
    }

    @Override
    public void onReceive(Object msg) throws Exception {

        if (msg instanceof String) {
            log.info("hello " + msg + "!");
        } else {
            unhandled(msg);
        }
    }
}
