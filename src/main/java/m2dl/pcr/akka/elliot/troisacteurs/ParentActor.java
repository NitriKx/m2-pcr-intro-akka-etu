package m2dl.pcr.akka.elliot.troisacteurs;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Procedure;

public class ParentActor extends UntypedActor {
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private ActorRef nameActorRefHello;
    private ActorRef nameActorRefGoodbye;

    public ParentActor() {
        log.info("ParentActor constructor");

        nameActorRefHello = getContext().actorOf(Props.create(SonHelloActor.class), "hello-actor");
        nameActorRefGoodbye = getContext().actorOf(Props.create(SonGoodbyeActor.class), "goodbye-actor");
    }

    Procedure<Object> hello = new Procedure<Object>() {
        public void apply(Object msg) throws Exception {
            if (msg instanceof String) {
                nameActorRefHello.tell(msg,getSelf());
                getContext().become(goodbye,false);
            } else {
                unhandled(msg);
            }
        }
    };

    Procedure<Object> goodbye = new Procedure<Object>() {
        public void apply(Object msg) throws Exception {
            if (msg instanceof String) {
                nameActorRefGoodbye.tell(msg,getSelf());
                getContext().unbecome();
            } else {
                unhandled(msg);
            }
        }
    };

    @Override
    public void onReceive(Object msg) throws Exception {
        hello.apply(msg);
    }
}
