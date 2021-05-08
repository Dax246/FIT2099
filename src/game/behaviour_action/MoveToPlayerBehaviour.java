package game.behaviour_action;

import edu.monash.fit2099.engine.*;
import game.Behaviour;
import game.Player;
import game.Util;

import java.util.ArrayList;

/**
 * A class that figures out a MoveAction that will move the actor one step 
 * closer to a target Actor.
 */
public class MoveToPlayerBehaviour implements Behaviour {
	private String foodType;

	public Location locatePlayer(Actor actor, GameMap map) {
		ArrayList<Location> playerLocations = Util.locateObjects(map.locationOf(actor), "Actor");
		for (Location playerLocation : playerLocations) {
			if (playerLocation.getActor() instanceof Player) {
				return playerLocation;
			}
		}
		return null;
	}

	@Override
	public Action getAction(Actor actor, GameMap map) {
		Location destination = locatePlayer(actor, map);
		System.out.println(actor.toString() + " at (" + map.locationOf(actor).x() + ", " + map.locationOf(actor).y() + ") moving towards (" + destination.x() + ", " + destination.y() + ")");
		Behaviour moveToLocation = new MoveToLocationBehaviour(destination);
		return moveToLocation.getAction(actor, map);
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