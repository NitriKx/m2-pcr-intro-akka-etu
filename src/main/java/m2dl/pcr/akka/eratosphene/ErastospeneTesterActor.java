package m2dl.pcr.akka.eratosphene;

import akka.actor.ActorRef;
import akka.actor.PoisonPill;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Procedure;
import m2dl.pcr.akka.dansunnouveaupackage.GoodbyeActor;
import m2dl.pcr.akka.dansunnouveaupackage.HelloActor;

/**
 * Created by Leo on 26/05/16.
 */
public class ErastospeneTesterActor extends UntypedActor {
    LoggingAdapter log = Logging.getLogger(getContext().system(), ErastospeneTesterActor.class);

    private ActorRef erastospeneTesterFilsActor;
    private int value;
    public ErastospeneTesterActor(int value) {
        this.value = value;
        log.info("Nouveau nombre premier "+ value);
    }


    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg instanceof String) {
            if (erastospeneTesterFilsActor != null) {
                erastospeneTesterFilsActor.tell(msg, null);
                //Thread.sleep(1000);
            } else {
                getContext().system().terminate();
            }
            //getContext().stop(getSelf());
        } if (msg instanceof Integer) {
            int val = Integer.parseInt(msg.toString());
            if (val % value == 0) {
                // silent this number, it's not a "prime/first number"
            } else {
                if (erastospeneTesterFilsActor != null) {
                    erastospeneTesterFilsActor.tell(msg,getSelf());
                } else {
                    erastospeneTesterFilsActor = getContext().actorOf(Props.create(ErastospeneTesterActor.class, val), "hello-goodbye-router-actor-" + val);
                }
            }
        } else {
            unhandled(msg);
        }
    }

}
