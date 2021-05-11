package game.behaviour_action;

import edu.monash.fit2099.engine.*;
import game.Fruit;
import game.dinosaurs.Brachiosaur;
import game.dinosaurs.Dinosaur;
import game.dinosaurs.Stegosaur;
import game.groundPackage.Bush;
import game.groundPackage.Lake;
import game.groundPackage.Tree;

/**
 * @author Allan Chan and Damien Ambegoda
 * @version 1.0.0
 * @see game.dinosaurs.Dinosaur
 * Action to sip fruit.
 */
public class SipWaterAction extends Action {
    private Location sipLocation;

    public SipWaterAction(Location sipLocation) {
        this.sipLocation = sipLocation;
    }

    /**
     * Removes water from lake that dinosaurs sip from
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String that will be printed on menu
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Dinosaur dinosaurActor = (Dinosaur) actor;
        Lake lakeSipLocation = (Lake) sipLocation.getGround();

        assert lakeSipLocation.getCapacity() > 0;

        lakeSipLocation.setCapacity(lakeSipLocation.getCapacity() - 1);
        dinosaurActor.quench();

        return actor.toString() + " sipped water from (" + sipLocation.x() + ", " + sipLocation.y() + ")";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + " is sipping fruit";
    }
}

