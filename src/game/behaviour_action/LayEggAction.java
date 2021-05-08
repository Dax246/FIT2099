package game.behaviour_action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.dinosaurs.*;

/**
 * @author Allan Chan and Damien Ambegoda
 * @version 1.0.0
 * @see Egg
 * Action to lay an egg
 */
public class LayEggAction extends Action {
    /**
     * Lays an egg at actor's location
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String to be printed
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Location actorLocation = map.locationOf(actor);
        if (actor instanceof Stegosaur){
            map.locationOf(actor).addItem(new StegEgg());
            return "Stegosaur has laid an egg at (" + map.locationOf(actor).x() + ", " + map.locationOf(actor).y() + ")";
        }

        else if (actor instanceof Brachiosaur){
            map.locationOf(actor).addItem(new BrachEgg());
            return "Brachiosaur has laid an egg at (" + map.locationOf(actor).x() + ", " + map.locationOf(actor).y() + ")";
        }

        else {
            assert (actor instanceof Allosaur) : "Egg layer is not a valid dinosaur!";
            map.locationOf(actor).addItem(new AlloEgg());
            return "Allosaur has laid an egg at (" + map.locationOf(actor).x() + ", " + map.locationOf(actor).y() + ")";
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Dinosaur laying egg";
    }
}
