package game.behaviour_action;

import edu.monash.fit2099.engine.*;
import game.Util;
import game.dinosaurs.Allosaur;
import game.dinosaurs.Corpse;
import game.dinosaurs.Dinosaur;
import game.dinosaurs.Pterodactyl;

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
        String verb = " ate ";
        Item itemToEat = Util.retrieveItem("Egg", actorLocation.getItems());

        String foodEaten = "";
        if (itemToEat != null) {
            actor.heal(10);
            actorLocation.removeItem(itemToEat);
            foodEaten = "Egg";
        }
        else {
            itemToEat = Util.retrieveItem("Corpse", actorLocation.getItems());
            if (itemToEat != null) {
                Corpse corpse = (Corpse) itemToEat;

                if (actor instanceof Pterodactyl) {
                    actor.heal(10);
                    corpse.pteroNibble();
                    if (corpse.getCorpseHeal() == 0) {
                        actorLocation.removeItem(itemToEat);
                    }
                    else {
                        verb = " nibbled on ";
                    }
                    foodEaten = corpse.getCorpseDinoName() + " corpse";
                }
                else {
                    actor.heal(corpse.getCorpseHeal());
                    actorLocation.removeItem(itemToEat);
                    foodEaten = corpse.getCorpseDinoName() + " corpse";
                }
            }
            else {
                //instantly devour Pterodactyl
                assert actor instanceof Allosaur;
                for (Exit exit : actorLocation.getExits()) {
                    if (exit.getDestination().getActor() instanceof Pterodactyl) {
                        actor.heal(100);
                        map.removeActor(exit.getDestination().getActor());
                        foodEaten = "Pterodactyl";
                        break;
                    }
                }
            }
        }

        return actor.toString() + verb + foodEaten + " at (" + actorLocation.x() + ", " + actorLocation.y() + ")";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + " is eating non fruit";
    }
}

