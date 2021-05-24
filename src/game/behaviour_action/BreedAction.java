package game.behaviour_action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.dinosaurs.*;


/**
 * @author Allan Chan and Damien Ambegoda
 * @version 2.0.0
 * @see BreedBehaviour
 */
public class BreedAction extends Action {
    /**
     * Stores the mate of the dinosaur
     */
    private Dinosaur mate;

    /**
     * Constructor of the action
     * @param mate actor to mate with
     */
    public BreedAction(Dinosaur mate) {
        this.mate = mate;
    }

    /**
     * Method that sets a counter for when a female mate lays their egg.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Actor female = null;
        if (((Dinosaur) actor).getSex() == 'F'){
            female = actor;
        }
        else if (mate.getSex() == 'F'){
            female = mate;
        }
        assert !(female == null);
        // This action should not be called if the female is already pregnant
        if (((Dinosaur) female).getLayEggCounter() == 0) {
            if (female instanceof Stegosaur){
                ((Stegosaur) female).setLayEggCounter(10);
                return "Stegosaurs mate! Egg laid in 10 turns";
            }
            else if (female instanceof Brachiosaur){
                ((Brachiosaur) female).setLayEggCounter(30);
                return "Brachiosaurs mate! Egg laid in 30 turns";
            }
            else if (female instanceof Allosaur){
                ((Allosaur) female).setLayEggCounter(20);
                return "Allosaurs mate! Egg egg laid in 20 turns";
            }
            else if (female instanceof Pterodactyl){
                ((Pterodactyl) female).setLayEggCounter(10);
                return "Pterodactyls mate! Egg egg laid in 10 turns";
            }
            else {
                throw new AssertionError("Unexpected dinosaur falling pregnant");
            }
        }
        return null;
    }

    /**
     * Returns string to print on menu
     * @param actor The actor performing the action.
     * @return String to print on menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Dinosaur will mate";
    }
}
