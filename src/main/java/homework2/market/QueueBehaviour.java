package homework2.market;

import homework2.actor.Actor;

public interface QueueBehaviour {

    void takeInQueue(Actor actor);
    void takeOrders();
    void giveOrders();
    void releaseFromQueue();

}