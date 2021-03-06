package game.behaviour_action;

import edu.monash.fit2099.engine.*;
import game.Behaviour;
import game.Util;
import game.dinosaurs.Dinosaur;
import game.dinosaurs.Pterodactyl;

import java.util.ArrayList;

/**
 * @author Allan Chan and Damien Ambegoda
 * @version 2.0.0
 * @see Dinosaur
 * Behaviour that determines whether to sip water or move towards it.
 */
public class FindWaterBehaviour implements Behaviour {
	/**
	 * Returns action based on dinosaur and location of closest food.
	 * @param actor the Actor acting
	 * @param map the GameMap containing the Actor
	 * @return action to eat/move.
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		ArrayList<Location> waterLocations = Util.locateObjects(map.locationOf(actor), "Water");
		Location destination;
		if (waterLocations.size() > 0) {
			destination = waterLocations.get(0);
		} else {
			return null;
		}

		if (distance(destination, map.locationOf(actor)) == 0 && ((actor instanceof Pterodactyl) && ((Pterodactyl) actor).isFlying())) {
			return new SipWaterAction(destination);
		}

		if (distance(destination, map.locationOf(actor)) == 1) {
			if (!((actor instanceof Pterodactyl) && ((Pterodactyl) actor).isFlying())) {
				return new SipWaterAction(destination);
			}
		}
		Behaviour moveToLocation = new MoveToLocationBehaviour(destination);
		return moveToLocation.getAction(actor, map);
	}
}