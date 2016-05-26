package m2dl.pcr.akka.elliot.esrasthosthèsnnhes;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elliot on 26/05/2016.
 */
public class CribleActor extends UntypedActor {
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private int valeur;
    private List<Integer> arrayEntiers;
    boolean nextInstance;

    private ActorRef nameActorRef = null;

    public CribleActor(int valeur) {
        this.valeur = valeur;
        this.nextInstance = false;
        this.arrayEntiers = new ArrayList<Integer>();
        log.info("CribleActor constructor");
    }

    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg instanceof String) {
            if (msg.toString().equals("stop")) {
                stop();
            } else {
                work(msg.toString());
            }
        } else {
            unhandled(msg);
        }
    }

    public void work(String msg) {
        int msgInt = Integer.parseInt(msg.toString());
        int res = msgInt % valeur;
        log.info(msg+" modulo "+valeur+" = "+res);
        if (res == 0) {
            arrayEntiers.add(msgInt);
            log.info("Crible "+valeur+" : "+arrayEntiers.toString());
        } else {
            if (!nextInstance) {
                nameActorRef = getContext().actorOf(Props.create(CribleActor.class, Integer.parseInt(msg.toString())), "crible-"+msgInt+"-actor");
                nextInstance = true;
            } else {
                nameActorRef.tell(""+msgInt,getSelf());
            }
        }
    }

    public void stop() {
        if (nextInstance) {
            nameActorRef.tell("stop",getSelf());
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("Crible "+valeur+" : FERMETURE");
        this.getContext().stop(getSelf());
    }
}