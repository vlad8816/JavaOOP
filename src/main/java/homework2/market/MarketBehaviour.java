package homework2.market;

import homework2.actor.Actor;

import java.util.List;

public interface MarketBehaviour {

    void acceptToMarket(Actor actor);
    void releaseFromMarket(List<Actor> actors);
    void update();

}