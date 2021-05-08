package game.dinosaurs;


import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import game.Behaviour;
import game.behaviour_action.WanderBehaviour;

/**
 * A herbivorous dinosaur.
 *
 */
public class Brachiosaur extends Dinosaur {
	// Will need to change this to a collection if Brachiosaur gets additional Behaviours.
	private Behaviour behaviour = new WanderBehaviour();
	private int maxHitPoints = 160;

	/**
	 * Constructor.
	 * All Brachiosaur are represented by a 'b' and have 100 hit points.
	 *
	 * @param name the name of this Brachiosaur
	 */

	public Brachiosaur(String name, Boolean isBaby) {
		super(name, 'd', 160);
		setMaxUnconsciousTurns(15);
		setHungerThreshold(140);
		setBreedThreshold(70);
		if (isBaby) {
			this.setAge(0);
			this.hitPoints = 10;
		}
		else {
			this.setAge(50);
			this.hitPoints = 100;
		}
	}

	/**
	 * Figure out what to do next.
	 * 
	 * FIXME: Brachiosaur wanders around at random, or if no suitable MoveActions are available, it
	 * just stands there.  That's boring.
	 * 
	 * @see Actor#playTurn(Actions, Action, GameMap, Display)
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		Action wander = behaviour.getAction(this, map);

		if (wander != null)
			return wander;

		return new DoNothingAction();
	}

}
