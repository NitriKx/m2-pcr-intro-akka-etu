package m2dl.pcr.akka.partie3;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Procedure;
import m2dl.pcr.akka.stringservices.StringUtils;

/**
 * Created by julien on 26/05/16.
 */
public class ComposerActor extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), ErreurControlProviderActor.class);

    private ActorRef crypteur;
    private ActorRef erreur;
    private ActorRef recepteur;

    public ComposerActor(ActorRef crypteur, ActorRef erreur, ActorRef recepteur) {
        this.crypteur = crypteur;
        this.erreur = erreur;
        this.recepteur = recepteur;
    }

    Procedure<Object> crypter = new Procedure<Object>() {
        public void apply(Object msg) throws Exception {
            if (msg instanceof Message) {
                ((Message) msg).setRecepteur(getSelf());
                crypteur.tell(msg,getSelf());
                getContext().become(controller,false);
            } else {
                unhandled(msg);
            }
        }
    };

    Procedure<Object> controller = new Procedure<Object>() {
        public void apply(Object msg) throws Exception {
            if (msg instanceof Message) {
                ((Message) msg).setRecepteur(getSelf());
                erreur.tell(msg, getSelf());
                getContext().become(envoyer, false);
            } else {
                unhandled(msg);
            }
        }
    };

    Procedure<Object> envoyer =  new Procedure<Object>() {
        public void apply(Object msg) throws Exception {
            if (msg instanceof Message) {
                recepteur.tell(msg, getSelf());
                getContext().unbecome();
            } else {
                unhandled(msg);
            }
        }
    };

    @Override
    public void onReceive(Object msg) throws Exception {
        crypter.apply(msg);
    }
}
