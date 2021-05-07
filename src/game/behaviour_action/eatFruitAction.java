package game.behaviour_action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.Util;
import game.dinosaurs.Brachiosaur;

import java.util.ArrayList;

public class eatFruitAction extends Action {


    @Override
    public String execute(Actor actor, GameMap map) {
        Location actorLocation = map.locationOf(actor);
        if (actor instanceof Brachiosaur){
            ArrayList<Location> locations = Util.locateObjects(actorLocation, "fruit");
            for (Location location: locations){

            }
        }
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
