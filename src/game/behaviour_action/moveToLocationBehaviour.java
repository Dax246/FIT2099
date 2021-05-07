package game.behaviour_action;

import edu.monash.fit2099.engine.*;
import game.Behaviour;

/**
 * A class that figures out a MoveAction that will move the actor one step 
 * closer to a target Actor.
 */
public class moveToLocationBehaviour implements Behaviour {

	private Location target;

	/**
	 * Constructor.
	 *
	 * @param target the Location to move towards
	 */
	public moveToLocationBehaviour(Location target) {
		this.target = target;
	}

	@Override
	public Action getAction(Actor actor, GameMap map) {
		Location here = map.locationOf(actor);

		int currentDistance = distance(here, this.target);
		if (currentDistance == 1) {
			throw new AssertionError("Shouldn't need to move to location that is in reach");
		}

		for (Exit exit : here.getExits()) {
			Location destination = exit.getDestination();
			if (destination.canActorEnter(actor)) {
				int newDistance = distance(destination, this.target);
				if (newDistance < currentDistance) {
					return new MoveActorAction(destination, exit.getName());
				}
			}
		}

		return null;
	}

	/**
	 * Compute the Manhattan distance between two locations.
	 * 
	 * @param a the first location
	 * @param b the first location
	 * @return the number of steps between a and b if you only move in the four cardinal directions.
	 */
	private int distance(Location a, Location b) {
		return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
	}
}