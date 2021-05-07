package game.behaviour_action;

import edu.monash.fit2099.engine.*;
import game.dinosaurs.Dinosaur;

import java.util.List;

public class FeedDinoAction extends Action {

    @Override
    public String execute(Actor actor, GameMap map) {
        Location actorLocation = map.locationOf(actor);
        List<Exit> exits = actorLocation.getExits();
        for (Exit exit: exits){
            if (exit.getDestination().containsAnActor() && exit.getDestination().getActor() instanceof Dinosaur){

            }
        }
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
