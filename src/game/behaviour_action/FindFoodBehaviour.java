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
 * @author Allan Chan and Damien Ambegoda
 * @version 1.0.0
 * @see Dinosaur
 * Behaviour that determines which eat action or movement to do.
 */
public class FindFoodBehaviour implements Behaviour {
	/**
	 * Determines closest viable item for dinosaur.
	 * @param actor Actor that's finding food.
	 * @param map GameMap actor is on.
	 * @return Closest viable item for actor.
	 */
	private Location itemDestination(Actor actor, GameMap map) {
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
		else if (actor instanceof Pterodactyl) {
			ArrayList<String> diet = new ArrayList<String>();
			diet.add("Fish");
			diet.add("Corpse");
			diet.add("Egg");
			return nonFoodDestination(actor, map, diet);
		}
		else {
			ArrayList<String> diet = new ArrayList<String>();
			diet.add("Corpse");
			diet.add("Egg");
			Location closestLocation = nonFoodDestination(actor, map, diet);

			ArrayList<Location> ActorLocations= Util.locateObjects(actorLocation, "Actor");

			for (Location stegLocation: ActorLocations) {
				if (stegLocation.getActor() instanceof Stegosaur) {
					Allosaur alloActor = (Allosaur) actor;
					if (!alloActor.getCannotAttack().containsKey((Stegosaur) stegLocation.getActor())) {
						if (closestLocation == null) {
							closestLocation = stegLocation;
						} else if (distance(actorLocation, stegLocation) < distance(actorLocation, closestLocation)) {
							closestLocation = stegLocation;
							break;
						}
					}
				}
			}
			return closestLocation;
		}
		return null;
	}

	private Location nonFoodDestination(Actor actor, GameMap map, ArrayList<String> diet) {
		Location actorLocation = map.locationOf(actor);
		Location closestDestination = null;

		for (String item : diet) {
			ArrayList<Location> itemLocations = Util.locateObjects(actorLocation, item);
			if (itemLocations.size() > 0) {
				if (closestDestination == null || distance(actorLocation, itemLocations.get(0)) < distance(actorLocation, closestDestination)) {
					closestDestination = itemLocations.get(0);
				}
			}
		}
		return closestDestination;
	}

	/**
	 * Returns action based on dinosaur and location of closest food.
	 * @param actor the Actor acting
	 * @param map the GameMap containing the Actor
	 * @return action to eat/move.
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		Location destination = itemDestination(actor, map);
		if (destination == null) {
			return null;
		} else if (distance(destination, map.locationOf(actor)) == 0) {
			if (actor instanceof Allosaur || actor instanceof Pterodactyl) {
				return new EatNonFruitAction();
			} else {
				return new EatFruitAction();
			}
		} else if (distance(destination, map.locationOf(actor)) == 1
				&& map.getActorAt(destination) instanceof Stegosaur
				&& actor instanceof Allosaur) {
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