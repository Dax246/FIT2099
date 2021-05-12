package game.dinosaurs;

import edu.monash.fit2099.engine.*;
import game.groundPackage.Tree;

/**
 * @author Allan Chan and Damien Ambegoda
 * @version 1.0.0
 * @see Dinosaur
 * A flying carnivorous dinosaur.
 */
public class Pterodactyl extends Dinosaur {
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
		setHungerThreshold(70);
		setBreedThreshold(50);
		setMaxWaterLevel(100);
		if (isBaby) {
			this.setAge(0);
			this.hitPoints = 20;
		}
		else {
			this.setAge(50);
			this.hitPoints = 50;
		}
	}

	public int getFlightTime() {
		return flightTime;
	}

	public void setFlightTime(int flightTime) {
		this.flightTime = flightTime;
	}

	public boolean isFlying() {
		return flightTime < 30;
	}

	/**
	 * Determines which action to take this turn. Overrides super to decrement Stegosaurs it cannot attack
	 * @see Actor#playTurn(Actions, Action, GameMap, Display)
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		if (map.locationOf(this).getGround() instanceof Tree) {
			this.flightTime = 0;
		} else if (isFlying()) {
			this.flightTime += 1;
		}
		return super.playTurn(actions, lastAction, map, display);
	}
}
