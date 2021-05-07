package game.behaviour_action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.dinosaurs.*;

public class LayEggAction extends Action {

    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor instanceof Stegosaur){
            map.locationOf(actor).addItem(new StegEgg());
            return "Stegosaur has laid an egg";
        }

        else if (actor instanceof Brachiosaur){
            map.locationOf(actor).addItem(new BrachEgg());
            return "Brachiosaur has laid an egg";
        }

        else if (actor instanceof Allosaur){
            map.locationOf(actor).addItem(new AlloEgg());
            return "Allosaur has laid an egg";
        }

        return "Egg has not been laid";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Dinosaur laying egg";
    }
}
