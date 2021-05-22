package game.behaviour_action;

import edu.monash.fit2099.engine.*;
import game.Fruit;
import game.dinosaurs.Brachiosaur;
import game.dinosaurs.Stegosaur;
import game.groundPackage.Bush;
import game.groundPackage.Flora;
import game.groundPackage.Tree;

/**
 * @author Allan Chan and Damien Ambegoda
 * @version 1.0.0
 * @see game.dinosaurs.Dinosaur
 * Action to remove fruit that dinosaurs eat.
 */
public class EatFruitAction extends Action {
    /**
     * Removes fruit that dinosaurs eat
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String that will be printed on menu
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Location actorLocation = map.locationOf(actor);
        boolean validEatFruitAction = false;
        if (actor instanceof Stegosaur) {
            if (actorLocation.getGround() instanceof Bush) {
                boolean fruitToEat = ((Bush) actorLocation.getGround()).harvestFruit(actorLocation, 'B');
                if (fruitToEat) {
                    actor.heal(10);
                    validEatFruitAction = true;
                }
            } else if (actorLocation.getGround() instanceof Tree) {
                for (Item item : actorLocation.getItems()){
                    if (item instanceof Fruit && ((Fruit) item).getStoredLocation() == 'G') {
                        actor.heal(10);
                        actorLocation.removeItem(item);
                        ((Tree) actorLocation.getGround()).decrementNumberOfFruit();
                        validEatFruitAction = true;
                        break;
                    }
                }
            }
        } else if (actor instanceof Brachiosaur) {
            if (actorLocation.getGround() instanceof Tree) {
                boolean fruitStill = true;
                while (fruitStill) {
                    boolean fruitToEat = ((Tree) actorLocation.getGround()).harvestFruit(actorLocation, 'T');
                    if (fruitToEat) {
                        actor.heal(5);
                        validEatFruitAction = true;
                    }
                    else {
                        fruitStill = false;
                    }
                }
            }
        }

        if (validEatFruitAction) {
            return actor.toString() + " ate Fruit at (" + actorLocation.x() + ", " + actorLocation.y() + ")";
        } else {
            return actor.toString() + " failed to eat Fruit at (" + actorLocation.x() + ", " + actorLocation.y() + ")";
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + " is eating fruit";
    }
}

