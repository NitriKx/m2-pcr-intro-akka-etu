package m2dl.pcr.akka.julien_exercice1point2;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Procedure;


public class ParentActor extends UntypedActor {
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private ActorRef goodByActor;
    private ActorRef helloActor;


    public ParentActor() {
        log.info("Parent constructor");
        goodByActor = getContext().actorOf(Props.create(GoodbyEnfantActor.class), "GoodBy-actor");
        helloActor = getContext().actorOf(Props.create(HelloEnfantActor.class), "Hello-Actor");

    }

    @Override
    public void onReceive(Object msg) throws Exception {

        log.info("Received msg: " + msg);

        if (msg instanceof String) {
            hello.apply(msg);
        } else {
            unhandled(msg);
        }
    }

    Procedure<Object> hello = new Procedure<Object>() {
        public void apply(Object msg) throws Exception {
            if (msg instanceof String) {
                log.info("Hello " + msg + "!");
                helloActor.tell(msg, getSelf());
                getContext().become(goodbye,false);
            } else {
                unhandled(msg);
            }
        }
    };

    Procedure<Object> goodbye = new Procedure<Object>() {
        public void apply(Object msg) throws Exception {
            if (msg instanceof String) {
                log.info("Good bye " + msg + "!");
                goodByActor.tell(msg, getSelf());
                getContext().unbecome();
            } else {
                unhandled(msg);
            }
        }
    };


}
