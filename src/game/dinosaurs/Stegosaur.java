package game.dinosaurs;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import game.AttackAction;
import game.Behaviour;
import game.behaviour_action.WanderBehaviour;

import java.util.Random;

/**
 * A herbivorous dinosaur.
 *
 */
public class Stegosaur extends Dinosaur {
	// Will need to change this to a collection if Stegosaur gets additional Behaviours.
	private Behaviour behaviour = new WanderBehaviour();
	private int age;

	/** 
	 * Constructor.
	 * All Stegosaurs are represented by an 's' and have 100 hit points.
	 * 
	 * @param name the name of this Stegosaur
	 */

	public Stegosaur(String name, Boolean isBaby) {
		//initialise gender, age, adultAge, deathCounter
		super(name, 's', 100);
		if (isBaby) {
			this.setAge(0);
			this.hitPoints = 10;
		}
		else {
			this.setAge(30);
			this.hitPoints = 50;
		}
	}

	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		return new Actions(new AttackAction(this));
	}

	/**
	 * Figure out what to do next.
	 * 
	 * FIXME: Stegosaur wanders around at random, or if no suitable MoveActions are available, it
	 * just stands there.  That's boring.
	 * 
	 * @see edu.monash.fit2099.engine.Actor#playTurn(Actions, Action, GameMap, Display)
	 */

	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		super.playTurn(actions, lastAction, map, display);
		this.age ++;

		//if unconscious, increment unconsciousTurnCounter
			//if unconsciousTurnCounter > maxTurnsDeath: make Corpse(dinosaur), Return DoNothingAction()

		//decrement hitpoints
			//if hitpoints == 0: unconscious = true, Return DoNothingAction

		//if next to mate and hitPoints > minHitPointsBreeding
			//Return breedAction


		//else if steg/brach and next to fruit or allo and next to steg/allo
			//Return eatFruitAction
		//else if food level > 90 (140 for brachiosaur)
			//Return findMateBehaviour.getAction
		//else
			//Return findFruitBehaviour.getAction if not allosaur
			//Return findFoodBehaviour.getAction if allosaur (corpse, egg, steg)


//		Action wander = behaviour.getAction(this, map);
//
//		if (wander != null)
//			return wander;

		return new DoNothingAction();
	}

}
