package game.behaviour_action;

import edu.monash.fit2099.engine.*;
import game.Util;
import game.dinosaurs.Corpse;
import game.dinosaurs.Dinosaur;

/**
 * @author Allan Chan and Damien Ambegoda
 * @version 1.0.0
 * @see Dinosaur
 * Action to remove item that an allosaur eats.
 */
public class EatNonFruitAction extends Action {
    /**
     * Removes item that allosaur eats.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String result that will be printed in menu
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Location actorLocation = map.locationOf(actor);
        Item itemToEat = Util.retrieveItem("Egg", actorLocation.getItems());

        String foodEaten;
        if (itemToEat != null) {
            actor.heal(10);
            actorLocation.removeItem(itemToEat);
            foodEaten = "Egg";
        }
        else {
            itemToEat = Util.retrieveItem("Corpse", actorLocation.getItems());
            if (itemToEat != null) {
                Corpse corpse = (Corpse) itemToEat;
                if (corpse.getCorpseOf() == 1) {
                    actor.heal(50);
                    actorLocation.removeItem(itemToEat);
                    foodEaten = "Stegosaur corpse";
                }
                else if (corpse.getCorpseOf() == 2) {
                    actor.heal(((Dinosaur) actor).getMaxHitPoints());
                    actorLocation.removeItem(itemToEat);
                    foodEaten = "Brachiosaur corpse";
                }
                else {
                    actor.heal(50);
                    actorLocation.removeItem(itemToEat);
                    foodEaten = "Allosaur corpse";
                }
            }
            else {
                throw new AssertionError("No food source nearby for allosaur at: (" + actorLocation.x() + ", " + actorLocation.y() + ")");
            }
        }

        return actor.toString() + " ate " + foodEaten + " at (" + actorLocation.x() + ", " + actorLocation.y() + ")";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + " is eating non fruit";
    }
}

