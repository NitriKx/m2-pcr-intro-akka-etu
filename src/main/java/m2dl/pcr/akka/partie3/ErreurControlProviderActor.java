package m2dl.pcr.akka.partie3;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import m2dl.pcr.akka.stringservices.StringUtils;

/**
 * Created by julien on 26/05/16.
 */
public class ErreurControlProviderActor extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), ErreurControlProviderActor.class);

    public ErreurControlProviderActor() {
    }


    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg instanceof Message) {
            Message message = (Message) msg;
            String messageSansCtrl = StringUtils.ajouteCtrl(message.getMessage());
            message.setMessage(messageSansCtrl);
            message.getRecepteur().tell(message, getSelf());
        } else {
            unhandled(msg);
        }
    }
}
