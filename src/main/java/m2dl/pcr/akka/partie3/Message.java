package m2dl.pcr.akka.partie3;

import akka.actor.ActorRef;

/**
 * Created by julien on 26/05/16.
 */
public class Message {

    ActorRef actorRef;
    String message;

    public Message(ActorRef actorRef, String message) {
        this.actorRef = actorRef;
        this.message = message;
    }

    public ActorRef getActorRef() {
        return actorRef;
    }

    public void setActorRef(ActorRef actorRef) {
        this.actorRef = actorRef;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
