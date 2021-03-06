package m2dl.pcr.akka.julien_partie2;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import m2dl.pcr.akka.julien_exercice1point2.GoodbyEnfantActor;
import m2dl.pcr.akka.julien_exercice1point2.HelloEnfantActor;

/**
 * Created by julien on 26/05/16.
 */
public class CribleActor extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private ActorRef cribleSuivant;
    private int diviseur;



    public CribleActor(int diviseur) {
        this.diviseur = diviseur;
        this.cribleSuivant = null;
    }


    @Override
    public void onReceive(Object o) throws Exception {

        if(o instanceof String) {
            String msg = (String) o;
            if(msg.equals("KILL")) {
                cribleSuivant.tell("KILL", getSelf());
                log.info("Fin du crible " + diviseur);
                Thread.sleep(1000);
                getContext().stop(getSelf());
            }
        }

        if(o instanceof Integer) {
            int nb = (Integer) o;
            if(nb%diviseur != 0) {
                // Creer un nouvelle acteur si besoin
                if(cribleSuivant == null) {
                    log.info("Découverte d'un nouveau nombre premier : "+nb);
                    cribleSuivant = getContext().actorOf(Props.create(CribleActor.class, nb), "crible-"+nb);
                }
                cribleSuivant.tell(nb, getSelf());
            }
        } else {
            unhandled(o);
        }

    }
}
