package game.behaviour_action;

import edu.monash.fit2099.engine.*;
import game.Behaviour;
import game.Fruit;
import game.Util;
import game.dinosaurs.Brachiosaur;
import game.dinosaurs.Stegosaur;
import game.groundPackage.Bush;
import game.groundPackage.Tree;

import java.util.ArrayList;

/**
 * A class that figures out a MoveAction that will move the actor one step 
 * closer to a target Actor.
 */
public class FindFruitBehaviour implements Behaviour {

	private ArrayList<Location> fruitLocations;

	private Location fruitDestination(Actor actor, GameMap map) {
		fruitLocations = Util.locateObjects(map.locationOf(actor), "fruit");

		for (Location location: fruitLocations) {
			if (actor instanceof Stegosaur) {
				//check bushes + fallen
				if (location.getGround() instanceof Bush && ((Bush) location.getGround()).numberOfFruit() > 0) {
					return location;
				}
				else if (location.getGround() instanceof Tree) {
					for (Item item: location.getItems()) {
						if (item instanceof Fruit) {
							return location;
						}
					}
				}
			}
			else if (actor instanceof Brachiosaur) {
				//check trees
				if (location.getGround() instanceof Tree && ((Tree) location.getGround()).numberOfFruit() > 0) {
					return location;
				}
			}
			else {
				throw new AssertionError("Dinosaur is not an expected herbivore");
			}
		}
		return null;
	}

	@Override
	public Action getAction(Actor actor, GameMap map) {
		Location destination = fruitDestination(actor, map);

		if (destination == null) {
			return null;
		}
		else {
			Behaviour moveToLocation = new moveToLocationBehaviour(destination);
			return moveToLocation.getAction(actor, map);
		}
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