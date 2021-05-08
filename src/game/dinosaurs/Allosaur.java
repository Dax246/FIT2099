package game.dinosaurs;

import edu.monash.fit2099.engine.*;
import game.Behaviour;
import game.behaviour_action.FindFoodBehaviour;
import game.behaviour_action.WanderBehaviour;

import java.util.HashMap;

/**
 * A herbivorous dinosaur.
 *
 */
public class Allosaur extends Dinosaur {
	// Will need to change this to a collection if Allosaur gets additional Behaviours.
	private Behaviour behaviour = new WanderBehaviour();
	private HashMap<Stegosaur, Integer> cannotAttack = new HashMap<Stegosaur, Integer>();

	/**
	 * Constructor.
	 * All Allosaur are represented by a 'd' and have 100 hit points.
	 *
	 * @param name the name of this Stegosaur
	 */
	public Allosaur(String name, Boolean isBaby) {
		super(name, 'a', 100);
		setMaxUnconsciousTurns(20);
		setHungerThreshold(70);
		setBreedThreshold(50);
		if (isBaby) {
			this.setAge(0);
			this.hitPoints = 20;
		}
		else {
			this.setAge(50);
			this.hitPoints = 50;
		}
	}

	public HashMap<Stegosaur, Integer> getCannotAttack() {
		return cannotAttack;
	}

	public void setCannotAttack(HashMap<Stegosaur, Integer> cannotAttack) {
		this.cannotAttack = cannotAttack;
	}

	/**
	 * Figure out what to do next.
	 * 
	 * FIXME: Allosaur wanders around at random, or if no suitable MoveActions are available, it
	 * just stands there.  That's boring.
	 * 
	 * @see Actor#playTurn(Actions, Action, GameMap, Display)
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		//TODO: FOR STEG IN HASHMAP, DECREMENT VALUE. IF 0, REMOVE IT.

		Behaviour behaviour = new FindFoodBehaviour();
		return behaviour.getAction(this, map);
	}

}
