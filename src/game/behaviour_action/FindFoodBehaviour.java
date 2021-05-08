package game.behaviour_action;

import edu.monash.fit2099.engine.*;
import game.Behaviour;
import game.Fruit;
import game.Util;
import game.dinosaurs.*;
import game.groundPackage.Bush;
import game.groundPackage.Tree;

import java.util.ArrayList;

/**
 * A class that figures out a MoveAction that will move the actor one step 
 * closer to a target Actor.
 */
public class FindFoodBehaviour implements Behaviour {
	private String foodType;

	public Location itemDestination(Actor actor, GameMap map) {
		Location actorLocation = map.locationOf(actor);
		if (actor instanceof Stegosaur || actor instanceof Brachiosaur) {
			ArrayList<Location> fruitLocations = Util.locateObjects(actorLocation, "Fruit");
			for (Location location: fruitLocations) {
				if (actor instanceof Stegosaur) {
					if (location.getGround() instanceof Bush) {
						return location;
					} else if (location.getGround() instanceof Tree) {
						for (Item item : location.getItems()){
							if (item instanceof Fruit && ((Fruit) item).getStoredLocation() == 'G') {
								return location;
							}
						}
					}
				} else {
					if (location.getGround() instanceof Tree) {
						for (Item item : location.getItems()){
							if (item instanceof Fruit && ((Fruit) item).getStoredLocation() == 'T') {
								return location;
							}
						}
					}
				}
			}
		}
		else {
			ArrayList<Location> closestLocations = new ArrayList<>();
			ArrayList<Location> corpseLocations= Util.locateObjects(actorLocation, "Corpse");
			ArrayList<Location> eggLocations= Util.locateObjects(actorLocation, "Egg");
			ArrayList<Location> ActorLocations= Util.locateObjects(actorLocation, "Actor");
			for (Location corpseLocation: corpseLocations) {
				Item corpse = Util.retrieveItem("Corpse", corpseLocation.getItems());
				if (corpse != null) {
					closestLocations.add(corpseLocation);
					break;
				}
			}

			for (Location eggLocation: eggLocations) {
				Item corpse = Util.retrieveItem("Egg", eggLocation.getItems());
				if (corpse != null) {
					closestLocations.add(eggLocation);
					break;
				}
			}

			for (Location stegLocation: ActorLocations) {
				if (stegLocation.getActor() instanceof Stegosaur) {
					Allosaur alloActor = (Allosaur) actor;
					if (!alloActor.getCannotAttack().containsKey((Stegosaur) stegLocation.getActor())) {
						closestLocations.add(stegLocation);
						break;
					}
				}
			}

			Location closestLocation = null;

			for (Location location: closestLocations) {
				if (closestLocation == null) {
					closestLocation = location;
				}
				else if (location != null && distance(actorLocation, location) < distance(actorLocation, closestLocation)) {
					closestLocation = location;
				}
			}
			return closestLocation;
		}
		return null;
	}

	@Override
	public Action getAction(Actor actor, GameMap map) {
		Location destination = itemDestination(actor, map);
		if (destination != null) {
			System.out.println("Moving towards: " + destination.x() + ", " + destination.y());
		}

		if (destination == null) {
			return null;
		} else if (distance(destination, map.locationOf(actor)) == 0) {
			if (actor instanceof Allosaur) {
				return new EatNonFruitAction();
			} else {
				return new EatFruitAction();
			}
		} else if (distance(destination, map.locationOf(actor)) == 1 && map.getActorAt(destination) instanceof Stegosaur) {
			return new AttackAction(map.getActorAt(destination));
		}
		else {
			Behaviour moveToLocation = new MoveToLocationBehaviour(destination);
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