package m2dl.pcr.akka.partie3;

import akka.actor.ActorRef;

import java.io.Serializable;

/**
 * Created by julien on 26/05/16.
 */
public class Message implements Serializable {

    ActorRef recepteur;
    String message;

    public Message() {
        recepteur = null;
        message = null;
    }

    public Message(ActorRef recepteur,  String message) {
        this.recepteur = recepteur;
        this.message = message;
    }

    public ActorRef getRecepteur() {
        return recepteur;
    }

    public void setRecepteur(ActorRef recepteur) {
        this.recepteur = recepteur;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
