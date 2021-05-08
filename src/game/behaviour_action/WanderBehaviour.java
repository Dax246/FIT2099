package game.behaviour_action;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.*;
import game.Behaviour;

/**
 * @author Allan Chan and Damien Ambegoda
 * @version 1.0.0
 * @see game.dinosaurs.Dinosaur
 * A class that chooses a random exit to take.
 */
public class WanderBehaviour implements Behaviour {
	/**
	 * Returns a MoveAction to wander to a random location, if possible.  
	 * If no movement is possible, returns null.
	 * 
	 * @param actor the Actor enacting the behaviour
	 * @param map the map that actor is currently on
	 * @return an Action, or null if no MoveAction is possible
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		Random random = new Random();
		ArrayList<Action> actions = new ArrayList<Action>();
		
		for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
            	actions.add(exit.getDestination().getMoveAction(actor, "around", exit.getHotKey()));
            }
        }
		
		if (!actions.isEmpty()) {
			return actions.get(random.nextInt(actions.size()));
		}
		else {
			return new DoNothingAction();
		}

	}

}
