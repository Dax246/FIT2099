package game.behaviour_action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.dinosaurs.Allosaur;
import game.dinosaurs.Brachiosaur;
import game.dinosaurs.Dinosaur;
import game.dinosaurs.Stegosaur;

public class BreedAction extends Action {
    private Dinosaur mate;

    public BreedAction(Dinosaur mate) {
        this.mate = mate;
    }

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
                return "Brachiosaur mate! Egg laid in 30 turns";
            }
            else if (female instanceof Allosaur){
                ((Allosaur) female).setLayEggCounter(20);
                return "Allosaur mate! Egg egg laid in 20 turns";
            }
            else {
                throw new AssertionError("Unexpected dinosaur falling pregnant");
            }
        }
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Dinosaur will mate";
    }
}
