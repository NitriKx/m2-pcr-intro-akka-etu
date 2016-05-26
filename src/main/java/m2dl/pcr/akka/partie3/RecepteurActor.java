package m2dl.pcr.akka.partie3;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import m2dl.pcr.akka.stringservices.StringUtils;

import java.util.ArrayList;

/**
 * Created by julien on 26/05/16.
 */
public class RecepteurActor extends UntypedActor {
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public RecepteurActor() {
        log.info("RecepteurActor constructor");
    }

    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg instanceof Message) {
            Message message = (Message) msg;
            String stringSansCtrl = StringUtils.verifieCtrl(message.getMessage());
            String messageFinal;

            if (stringSansCtrl == null) {
                messageFinal = StringUtils.decrypte(message.getMessage());
            } else {
                messageFinal = StringUtils.decrypte(stringSansCtrl);
            }
            log.info("RECEPTEUR = "+messageFinal);
        }
    }
}
