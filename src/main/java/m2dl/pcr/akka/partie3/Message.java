package m2dl.pcr.akka.partie3;

import akka.actor.ActorRef;

/**
 * Created by julien on 26/05/16.
 */
public class Message {

    ActorRef recepteur;
    ActorRef crypteur;
    ActorRef controller;
    String message;

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
