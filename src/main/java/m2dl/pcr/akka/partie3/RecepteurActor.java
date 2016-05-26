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
        if (msg instanceof String) {
            Message message = (Message) msg;
            // teste si le message est verifiable
            String messageVerifie = StringUtils.verifieCtrl(message.getMessage());
            String messageDecrypte;
            if (messageVerifie == null) {
                // un erreur controller provider a déjà enlevé le caractere
                messageDecrypte = StringUtils.decrypte(message.getMessage());
            } else {
                messageDecrypte = StringUtils.decrypte(messageVerifie);
            }
            if (messageDecrypte != null) {
                log.info("Message reçu : " + messageDecrypte);
            } else {
                log.error("Le message ne contient pas de ctrl");
            }
        }
    }
}
