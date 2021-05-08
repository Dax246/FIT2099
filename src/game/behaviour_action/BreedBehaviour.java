package game.behaviour_action;

import edu.monash.fit2099.engine.*;
import game.Behaviour;
import game.Util;
import game.dinosaurs.Dinosaur;

import java.util.ArrayList;

/**
 * A class that figures out a MoveAction that will move the actor one step 
 * closer to a target Actor.
 */
public class BreedBehaviour implements Behaviour {

	public Location mateDestination(Actor actor, GameMap map) {
		Dinosaur currentActor = (Dinosaur) actor;
		ArrayList<Location> ActorLocations = Util.locateObjects(map.locationOf(actor), "Actor");
		for (Location actorLocation : ActorLocations) {
			Dinosaur mate = (Dinosaur) actorLocation.getActor();
			if (currentActor.getDisplayChar() == mate.getDisplayChar()
					&& currentActor.getSex() != mate.getSex()
					&& currentActor.isAdult()
					&& mate.isAdult()
					&& mate.isConscious()
					&& currentActor.getLayEggCounter() == 0
					&& mate.getLayEggCounter() == 0
			) {
				return actorLocation;
			}
		}
		return null;
	}

	@Override
	public Action getAction(Actor actor, GameMap map) {
		Location destination = mateDestination(actor, map);
		if (destination != null) {
			System.out.println("Moving to mate: " + destination.x() + ", " + destination.y());
		}

		if (destination == null) {
			return null;
		} else if (distance(destination, map.locationOf(actor)) == 1) {
			return new BreedAction((Dinosaur) destination.getActor());
		} else if (distance(destination, map.locationOf(actor)) > 1) {
			Behaviour moveToLocation = new MoveToLocationBehaviour(destination);
			return moveToLocation.getAction(actor, map);
		}
		else {
			throw new AssertionError("Should not have called behaviour");
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