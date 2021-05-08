package game.behaviour_action;

import edu.monash.fit2099.engine.*;
import game.Behaviour;
import game.Player;

/**
 * @author Allan Chan and Damien Ambegoda
 * @version 1.0.0
 * @see game.Player
 * A class that figures out a MoveAction that will move the actor one step 
 * closer to the player.
 */
public class MoveToPlayerBehaviour implements Behaviour {
	/**
	 * Gets location of player.
	 * @param map Gamemap player is on.
	 * @return Location of player.
	 */
	public Location locatePlayer(GameMap map) {
		for (int i = 0; i < map.getXRange().max(); i++) {
			for (int j = 0; j < map.getYRange().max(); j++) {
				if (map.isAnActorAt(map.at(i, j)) && map.getActorAt(map.at(i, j)) instanceof Player) {
					return map.at(i, j);
				}
			}
		}
		assert false : "Player does not exist in map";
		return null;
	}

	/**
	 * Calls MoveToBehaviour to get MoveActorAction
	 * @param actor the Actor acting
	 * @param map the GameMap containing the Actor
	 * @return Action to move towards player
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		Location destination = locatePlayer(map);
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