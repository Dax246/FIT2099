package game.behaviour_action;

import edu.monash.fit2099.engine.*;
import game.Behaviour;
import game.Util;
import game.dinosaurs.Dinosaur;

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
			if (map.locationOf(actor) != destination) {
				Behaviour moveToLocation = new MoveToLocationBehaviour(destination);
				Action nextAction = moveToLocation.getAction(actor, map);
				if (nextAction != null) {
					return nextAction;
				}
			}
		}
		return new DoNothingAction();
	}
}