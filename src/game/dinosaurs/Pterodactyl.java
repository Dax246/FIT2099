package game.dinosaurs;

import edu.monash.fit2099.engine.*;
import game.groundPackage.Tree;

/**
 * @author Allan Chan and Damien Ambegoda
 * @version 1.0.0
 * @see Dinosaur
 * A flying carnivorous dinosaur.
 */
public class Pterodactyl extends Dinosaur implements DinoAge {
	private int flightTime = 0;

	/**
	 * Constructor.
	 * All Pterodactyl are represented by an 'p' and have up to 100 hit points.
	 * @param name the name of this Stegosaur
	 * @param isBaby boolean that's true if Allosaur to create is a baby.
	 */
	public Pterodactyl(String name, Boolean isBaby) {
		//same stats as Allosaur
		super(name, 'p', 100);
		setMaxUnconsciousTurns(20);
		setHungerThreshold(90);
		setBreedThreshold(50);
		setMaxWaterLevel(100);
		if (isBaby) {
			this.setAge(0);
			this.hitPoints = 10;
		}
		else {
			this.setAge(30);
			this.hitPoints = 50;
		}
	}

	/**
	 * Checks age to determine if adult dinosaur or not
	 *
	 * @return boolean if adult
	 */
	public boolean isAdult() {
		return getAge() >= 30;
	}

	/**
	 * Checks if flying by looking at how many turns the pterodactyl has been flying for
	 *
	 * @return boolean if flying
	 */
	public boolean isFlying() {
		return flightTime < 30;
	}

	/**
	 * Determines which action to take this turn. Overrides super to decrement Stegosaurs it cannot attack
	 * @see Actor#playTurn(Actions, Action, GameMap, Display)
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		if (!isFlying() && map.locationOf(this).getGround() instanceof Tree) {
			this.flightTime = 0;
		} else if (isFlying()) {
			this.flightTime += 1;
		}
		return super.playTurn(actions, lastAction, map, display);
	}
}
