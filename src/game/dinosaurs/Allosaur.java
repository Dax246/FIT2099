package game.dinosaurs;

import edu.monash.fit2099.engine.*;
import game.AttackAction;
import game.Behaviour;
import game.behaviour_action.WanderBehaviour;

/**
 * A herbivorous dinosaur.
 *
 */
public class Allosaur extends Dinosaur {
	// Will need to change this to a collection if Allosaur gets additional Behaviours.
	private Behaviour behaviour = new WanderBehaviour();

	/**
	 * Constructor.
	 * All Allosaur are represented by a 'd' and have 100 hit points.
	 *
	 * @param name the name of this Stegosaur
	 */
	public Allosaur(String name) {
		super(name, 'a', 100, 50);
		this.setAge(50);
	}

	//Constructor for baby allosaur
	public Allosaur(String name, int startingHitPoints) {
		super(name, 'a', 100, startingHitPoints);
		this.setAge(0);
	}

	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		return new Actions(new AttackAction(this));
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
		Action wander = behaviour.getAction(this, map);
		if (wander != null)
			return wander;

		return new DoNothingAction();
	}

}
