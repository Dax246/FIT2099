package game.behaviour_action;

import edu.monash.fit2099.engine.*;
import game.Fruit;
import game.dinosaurs.Brachiosaur;
import game.dinosaurs.Stegosaur;
import game.groundPackage.Bush;
import game.groundPackage.Tree;

public class EatFruitAction extends Action {


    @Override
    public String execute(Actor actor, GameMap map) {
        Location actorLocation = map.locationOf(actor);
        boolean validEatFruitAction = false;
        if (actor instanceof Stegosaur) {
            if (actorLocation.getGround() instanceof Bush) {
                Fruit fruitToEat = ((Bush) actorLocation.getGround()).harvestFruit(actorLocation);
                if (fruitToEat != null) {
                    actor.heal(10);
                    validEatFruitAction = true;
                }
            } else if (actorLocation.getGround() instanceof Tree) {
                for (Item item : actorLocation.getItems()){
                    if (item instanceof Fruit && ((Fruit) item).getStoredLocation() == 'G') {
                        actor.heal(10);
                        actorLocation.removeItem(item);
                        validEatFruitAction = true;
                        break;
                    }
                }
            }
        } else if (actor instanceof Brachiosaur) {
            if (actorLocation.getGround() instanceof Tree) {
                boolean fruitStill = true;
                while (fruitStill) {
                    Fruit fruitToEat = ((Tree) actorLocation.getGround()).harvestFruit(actorLocation);
                    if (fruitToEat != null) {
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
            return actor.toString() + " ate Fruit";
        } else {
            return actor.toString() + " failed to eat Fruit";
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + " is eating fruit";
    }
}

