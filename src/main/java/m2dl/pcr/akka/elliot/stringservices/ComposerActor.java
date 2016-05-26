package m2dl.pcr.akka.elliot.stringservices;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Procedure;

/**
 * Created by julien on 26/05/16.
 */
public class ComposerActor extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), ErreurControlProviderActor.class);

    private ActorRef crypteur;
    private ActorRef erreur;
    private ActorRef recepteur;

    public ComposerActor(ActorRef crypteur, ActorRef erreur) {
        this.crypteur = crypteur;
        this.erreur = erreur;
    }

    Procedure<Object> crypter = new Procedure<Object>() {
        public void apply(Object msg) throws Exception {
            if (msg instanceof Message) {
                Message msgMessage = (Message)msg;
                crypteur.tell(msgMessage.getMessage(),getSelf());
                getContext().become(controller,false);
            } else {
                unhandled(msg);
            }
        }
    };

    Procedure<Object> controller = new Procedure<Object>() {
        public void apply(Object msg) throws Exception {
            if (msg instanceof String) {
                String msgString = (String)msg;
                erreur.tell(msgString,getSelf());
                getContext().become(envoyer, false);
            } else {
                unhandled(msg);
            }
        }
    };

    Procedure<Object> envoyer =  new Procedure<Object>() {
        public void apply(Object msg) throws Exception {
            if (msg instanceof String) {
                String msgString = (String)msg;
                recepteur.tell(msgString,getSelf());
                getContext().unbecome();
            } else {
                unhandled(msg);
            }
        }
    };

    @Override
    public void onReceive(Object msg) throws Exception {
        this.recepteur = ((Message)msg).getRecepteur();
        crypter.apply(msg);
    }
}
