package game.dinosaurs;

import edu.monash.fit2099.engine.*;


import java.util.HashMap;
import java.util.HashSet;

/**
 * @author Allan Chan and Damien Ambegoda
 * @version 1.0.0
 * @see Dinosaur
 * A carnivorous dinosaur.
 */
public class Allosaur extends Dinosaur {
	/**
	 * Hashmap to keep track of Stegosaurs this Allosaur cannot attack and how long until they can.
	 */
	private HashMap<Stegosaur, Integer> cannotAttack = new HashMap<Stegosaur, Integer>();

	/**
	 * Constructor.
	 * All Allosaur are represented by an 'a' and have up to 100 hit points.
	 * @param name the name of this Stegosaur
	 * @param isBaby boolean that's true if Allosaur to create is a baby.
	 */
	public Allosaur(String name, Boolean isBaby) {
		super(name, 'a', 100);
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

	/**
	 * Getter of cannotAttack
	 * @return cannotAttack hashmap
	 */
	public HashMap<Stegosaur, Integer> getCannotAttack() {
		return new HashMap<>(cannotAttack);
	}

	/**
	 * Setter of cannotAttack
	 * @param cannotAttack hashmap of stegosaurs and turns until ALlosaur can attack them
	 */
	public void setCannotAttack(HashMap<Stegosaur, Integer> cannotAttack) {
		this.cannotAttack = cannotAttack;
	}

	/**
	 * Determines which action to take this turn. Overrides super to decrement Stegosaurs it cannot attack
	 * @see Actor#playTurn(Actions, Action, GameMap, Display)
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		cannotAttack.replaceAll((key, v) -> v - 1);

		HashSet<Stegosaur> removedSteg = new HashSet<>();
		for (Stegosaur key : cannotAttack.keySet()) {
			int turnsRemaining = cannotAttack.get(key);
			if (turnsRemaining == 0) {
				removedSteg.add(key);
			}
		}
		for (Stegosaur key: removedSteg) {
			cannotAttack.remove(key);
		}
		return super.playTurn(actions, lastAction, map, display);
	}
}
