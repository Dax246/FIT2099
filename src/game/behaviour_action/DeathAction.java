package game.behaviour_action;

import edu.monash.fit2099.engine.*;
import game.Fruit;
import game.dinosaurs.*;
import game.groundPackage.Bush;
import game.groundPackage.Tree;

public class DeathAction extends Action {


    @Override
    public String execute(Actor actor, GameMap map) {
        Dinosaur dinoActor = (Dinosaur) actor;
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
            else {
                throw new AssertionError("Unexpected actor dying.");
            }
            map.locationOf(actor).addItem(corpse);
            map.removeActor(actor);
        }
        else {
            throw new AssertionError("Dinosaur should not be dying");
        }
        return actor.toString() + " has died at (" + map.locationOf(actor).x() + ", " + map.locationOf(actor).y() + ")";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + " has died";
    }
}

