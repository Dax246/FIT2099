package game.behaviour_action;

import edu.monash.fit2099.engine.*;
import game.Fruit;
import game.Util;
import game.dinosaurs.Brachiosaur;
import game.dinosaurs.Stegosaur;
import game.groundPackage.Bush;
import game.groundPackage.Tree;

import java.util.ArrayList;

public class eatFruitAction extends Action {


    @Override
    public String execute(Actor actor, GameMap map) {
        Location actorLocation = map.locationOf(actor);
        boolean validEatFruitAction = false;
        if (actor instanceof Stegosaur) {
            if (actorLocation.getGround() instanceof Bush && ((Bush) actorLocation.getGround()).numberOfFruit() > 0) {
                ((Bush) actorLocation.getGround()).getFruit();
                actor.heal(10);
            } else if (actorLocation.getGround() instanceof Tree) {
                for (Item item : actorLocation.getItems()) {
                    if (item instanceof Fruit) {
                        actorLocation.getItems().remove(item);
                        actor.heal(10);
                        break;
                    }
                }
            }
        } else if (actor instanceof Brachiosaur) {
            if (actorLocation.getGround() instanceof Tree && ((Tree) actorLocation.getGround()).numberOfFruit() > 0) {
                while (((Brachiosaur) actor).getHitPoints() <= ((Brachiosaur) actor).getMaxHitPoints() - 5) {
                    ((Tree) actorLocation.getGround()).getFruit();
                    actor.heal(5);
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

