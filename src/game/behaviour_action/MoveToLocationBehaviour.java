package game.behaviour_action;

import edu.monash.fit2099.engine.*;
import game.Behaviour;
import game.dinosaurs.Pterodactyl;
import game.groundPackage.Lake;

/**
 * @author Allan Chan and Damien Ambegoda
 * @version 1.0.0
 * @see game.Player
 * A class that figures out a MoveAction that will move the actor one step 
 * closer to a target Actor.
 */
public class MoveToLocationBehaviour implements Behaviour {
	/**
	 * Location to get to
	 */
	private Location target;

	/**
	 * Constructor.
	 * @param target the Location to move towards
	 */
	public MoveToLocationBehaviour(Location target) {
		this.target = target;
	}

	/**
	 * Determines if any exits are any closer to target
	 * @param actor the Actor acting
	 * @param map the GameMap containing the Actor
	 * @return Action that moves towards target.
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		Location here = map.locationOf(actor);

		int currentDistance = distance(here, this.target);
		Location bestLocation = null;
		String exitName = "";

		for (Exit exit : here.getExits()) {
			Location destination = exit.getDestination();
			if (destination.canActorEnter(actor)) {
				int newDistance = distance(destination, this.target);
				if (newDistance < currentDistance) {
					if (!(destination.getGround() instanceof Lake)
							|| (actor instanceof Pterodactyl && ((Pterodactyl) actor).isFlying())) {
						if (bestLocation == null) {
							bestLocation = destination;
							exitName = exit.getName();
						}
						else if (newDistance < distance(here, bestLocation)) {
							bestLocation = destination;
							exitName = exit.getName();
						}
					}
				}
			}
		}
		if (bestLocation == null) {
			return null;
		}
		return new MoveActorAction(bestLocation, exitName);
	}
}