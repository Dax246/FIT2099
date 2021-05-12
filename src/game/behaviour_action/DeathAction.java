package game.behaviour_action;

import edu.monash.fit2099.engine.*;
import game.dinosaurs.*;

/**
 * @author Allan Chan and Damien Ambegoda
 * @version 1.0.0
 * @see Dinosaur
 * Action when a dinosaur is not conscious for long enough.
 */
public class DeathAction extends Action {
    /**
     * Creates a corpse of a starved dinosaur
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Dinosaur dinoActor = (Dinosaur) actor;
        Location deathLocation = map.locationOf(actor);
        if (dinoActor.getUnconsciousTurnsCounter() == dinoActor.getMaxUnconsciousTurns()) {
            Corpse corpse;
            if (dinoActor instanceof Stegosaur) {
                corpse = new Corpse(1);
            }
            else if (dinoActor instanceof Brachiosaur) {
                corpse = new Corpse(2);
            }
            else if (dinoActor instanceof Allosaur) {
                corpse = new Corpse(3);
            }
            else if (dinoActor instanceof Pterodactyl) {
                corpse = new Corpse(4);
            }
            else {
                throw new AssertionError("Unexpected actor dying.");
            }
            deathLocation.addItem(corpse);
            map.removeActor(actor);
        }
        else {
            throw new AssertionError("Dinosaur should not be dying");
        }
        return actor.toString() + " has died at (" + deathLocation.x() + ", " + deathLocation.y() + ")";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + " has died";
    }
}

