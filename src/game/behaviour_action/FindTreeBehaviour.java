package game.behaviour_action;

import edu.monash.fit2099.engine.*;
import game.Behaviour;
import game.Util;
import game.dinosaurs.Dinosaur;
import game.dinosaurs.Pterodactyl;

import java.util.ArrayList;

/**
 * @author Allan Chan and Damien Ambegoda
 * @version 1.0.0
 * @see Dinosaur
 * Behaviour that determines closest tree and moves towards it
 */
public class FindTreeBehaviour implements Behaviour {
	/**
	 * Returns action based on dinosaur and location of closest tree.
	 * @param actor the Actor acting
	 * @param map the GameMap containing the Actor
	 * @return action to eat/move.
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		ArrayList<Location> closestTrees = Util.locateObjects(map.locationOf(actor), "Tree");
		Location destination;
		if (closestTrees.size() > 0) {
			destination = closestTrees.get(0);
		} else {
			return null;
		}

		if (map.locationOf(actor) == destination) {
			return new DoNothingAction();
		}

		Behaviour moveToLocation = new MoveToLocationBehaviour(destination);
		return moveToLocation.getAction(actor, map);
	}
}