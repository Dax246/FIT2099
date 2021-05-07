package game.dinosaurs;

import edu.monash.fit2099.engine.*;
import game.Behaviour;
import game.behaviour_action.FindFoodBehaviour;
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
	public Allosaur(String name, Boolean isBaby) {
		super(name, 'a', 100);
		if (isBaby) {
			this.setAge(0);
			this.hitPoints = 20;
		}
		else {
			this.setAge(50);
			this.hitPoints = 50;
		}
	}

	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		return super.getAllowableActions(otherActor, direction, map);
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
		Behaviour behaviour = new FindFoodBehaviour();
		Action hunt = behaviour.getAction(this, map);
		return hunt;
//		Action wander = behaviour.getAction(this, map);
//		if (wander != null)
//			return wander;

//		return new DoNothingAction();
	}

}
