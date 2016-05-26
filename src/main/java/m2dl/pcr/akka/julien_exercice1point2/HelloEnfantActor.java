package m2dl.pcr.akka.julien_exercice1point2;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;


public class HelloEnfantActor extends UntypedActor {
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public HelloEnfantActor() {
        log.info("HELLOENFANT constructor");
    }

    @Override
    public void onReceive(Object msg) throws Exception {

        log.info("Received msg: " + msg);

        if (msg instanceof String) {
           log.info("Hello " + msg + "!");
        } else {
            unhandled(msg);
        }
    }


}
