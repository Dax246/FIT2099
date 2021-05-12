package game.behaviour_action;

import edu.monash.fit2099.engine.*;
import game.Behaviour;
import game.Util;
import game.dinosaurs.Dinosaur;
import game.dinosaurs.Pterodactyl;
import game.groundPackage.Tree;

import java.util.ArrayList;

/**
 * @author Allan Chan and Damien Ambegoda
 * @version 1.0.0
 * @see Dinosaur
 * A class that figures out whether to breed or move towards a mate.
 */
public class BreedBehaviour implements Behaviour {

	/**
	 * Finds the location of the closest mate.
	 * @param actor The actor looking for a mate.
	 * @param map The map the actor is on.
	 * @return Location of closest mate
	 */
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

	/**
	 * Returns the action based on breeding depending on map
	 * @param actor the Actor acting
	 * @param map the GameMap containing the Actor
	 * @return Action to execute
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		Location destination = mateDestination(actor, map);
		if (destination == null) {
			return null;
		}

		if (distance(destination, map.locationOf(actor)) == 1) {
			//Pterodactyls can only mate in adjacent trees
			if (!(actor instanceof Pterodactyl)
					|| ((map.locationOf(actor).getGround() instanceof Tree) && destination.getGround() instanceof Tree)) {
				return new BreedAction((Dinosaur) destination.getActor());
			}
		}

		if (distance(destination, map.locationOf(actor)) > 1) {
			Behaviour moveToLocation = new MoveToLocationBehaviour(destination);
			return moveToLocation.getAction(actor, map);
		}

		return null;
	}
}