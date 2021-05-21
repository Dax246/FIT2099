package game.behaviour_action;

import edu.monash.fit2099.engine.*;
import game.Behaviour;
import game.Fruit;
import game.Util;
import game.dinosaurs.*;
import game.groundPackage.Bush;
import game.groundPackage.Lake;
import game.groundPackage.Tree;

import java.util.ArrayList;
import java.util.Random;

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

			// can only eat fish if they are flying
			if (((Pterodactyl) actor).isFlying()) {
				diet.add("Fish");
			}
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

			for (Location iterActorLocation: ActorLocations) {
				if (iterActorLocation.getActor() instanceof Stegosaur) {
					Allosaur alloActor = (Allosaur) actor;
					if (!alloActor.getCannotAttack().containsKey((Stegosaur) iterActorLocation.getActor())) {
						if (closestLocation == null) {
							closestLocation = iterActorLocation;
						} else if (distance(iterActorLocation, iterActorLocation) < distance(iterActorLocation, closestLocation)) {
							closestLocation = iterActorLocation;
							break;
						}
					}
				} else if (iterActorLocation.getActor() instanceof Pterodactyl && !((Pterodactyl) iterActorLocation.getActor()).isFlying()) {
					closestLocation = iterActorLocation;
					break;
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
				if (actor instanceof Pterodactyl && item == "Corpse") {
					for (Location corpseLocation : itemLocations) {
						if (closestDestination == null || distance(actorLocation, corpseLocation) < distance(actorLocation, closestDestination)) {
							boolean foundCorpse = false;
							for (Exit exit : corpseLocation.getExits()) {
								if (!(exit.getDestination().containsAnActor() && exit.getDestination().getActor() instanceof Allosaur)) {
									foundCorpse = true;
									closestDestination = corpseLocation;
									break;
								}
							}
							if (foundCorpse) {
								break;
							}
						}
					}
				}
				else if (closestDestination == null || distance(actorLocation, itemLocations.get(0)) < distance(actorLocation, closestDestination)) {
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
		Location actorLocation = map.locationOf(actor);
		Location destination = itemDestination(actor, map);
		if (destination == null) {
			return null;
		}

		if (distance(destination, actorLocation) == 0) {
			if (actor instanceof Allosaur || actor instanceof Pterodactyl) {
				if (destination.getGround() instanceof Lake) {
					//swoop 0 - 2 fish instantly
					Random rand = new Random();
					int fishCaught = rand.nextInt(3);

					for (int i = 0; i < fishCaught; i++) {
						Item itemToEat = Util.retrieveItem("Fish", actorLocation.getItems());
						if (itemToEat != null) {
							actor.heal(5);
							actorLocation.removeItem(itemToEat);
						}
					}

					System.out.println(actor.toString() + " swooped into lake and grabbed " + fishCaught + " fish");

					//sip water
					SipWaterAction sipWaterAction = new SipWaterAction(actorLocation);
					sipWaterAction.execute(actor, map);

					return null;
				}
				return new EatNonFruitAction();
			} else {
				return new EatFruitAction();
			}
		}

		if (distance(destination, actorLocation) == 1 && actor instanceof Allosaur) {
			if (destination.getActor() instanceof Stegosaur) {
				return new AttackAction(map.getActorAt(destination));
			}
			return new EatNonFruitAction();
		}

		Behaviour moveToLocation = new MoveToLocationBehaviour(destination);
		return moveToLocation.getAction(actor, map);
	}
}