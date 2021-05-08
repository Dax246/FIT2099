package game.behaviour_action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.dinosaurs.*;

public class LayEggAction extends Action {

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
