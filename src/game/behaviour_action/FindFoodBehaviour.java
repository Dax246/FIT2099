package game.behaviour_action;

import edu.monash.fit2099.engine.*;
import game.Behaviour;
import game.Fruit;
import game.Util;
import game.dinosaurs.*;
import game.groundPackage.Bush;
import game.groundPackage.Tree;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A class that figures out a MoveAction that will move the actor one step 
 * closer to a target Actor.
 */
public class FindFoodBehaviour implements Behaviour {
	private Location actorLocation;

	private Location itemDestination(Actor actor, GameMap map) {
		this.actorLocation = map.locationOf(actor);
		if (actor instanceof Stegosaur || actor instanceof Brachiosaur) {
			ArrayList<Location> fruitLocations = Util.locateObjects(this.actorLocation, "fruit");
			for (Location location: fruitLocations) {
				if (actor instanceof Stegosaur) {
					//check bushes + fallen
					if (location.getGround() instanceof Bush && ((Bush) location.getGround()).numberOfFruit() > 0) {
						return location;
					} else if (location.getGround() instanceof Tree) {
						for (Item item : location.getItems()) {
							if (item instanceof Fruit) {
								return location;
							}
						}
					}
				} else {
					//check trees
					if (location.getGround() instanceof Tree && ((Tree) location.getGround()).numberOfFruit() > 0) {
						return location;
					}
				}
			}
			return null;
		}
		else {
			ArrayList<Location> closestLocations = new ArrayList<>();
			ArrayList<Location> corpseLocations= Util.locateObjects(this.actorLocation, "corpse");
			ArrayList<Location> eggLocations= Util.locateObjects(this.actorLocation, "egg");
			ArrayList<Location> ActorLocations= Util.locateObjects(this.actorLocation, "actor");
			for (Location corpseLocation: corpseLocations) {
				Item corpse = Util.retrieveItem("corpse", corpseLocation.getItems());
				if (corpse != null) {
					closestLocations.add(corpseLocation);
					break;
				}
			}

			for (Location eggLocation: eggLocations) {
				Item corpse = Util.retrieveItem("egg", eggLocation.getItems());
				if (corpse != null) {
					closestLocations.add(eggLocation);
					break;
				}
			}

			for (Location actorLocation: ActorLocations) {
				if (actorLocation.getActor() instanceof Stegosaur) {
					closestLocations.add(actorLocation);
					break;
				}
			}

			Location closestLocation = null;

			for (Location location: closestLocations) {
				if (closestLocation == null) {
					closestLocation = location;
				}
				else if (location != null && distance(this.actorLocation, location) < distance(this.actorLocation, closestLocation)) {
					closestLocation = location;
				}
			}

			return closestLocation;
		}
	}

	@Override
	public Action getAction(Actor actor, GameMap map) {
		Location destination = itemDestination(actor, map);
		System.out.println("Moving to: " + destination.x() + ", " + destination.y());

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