package game.dinosaurs;

import edu.monash.fit2099.engine.*;


import java.util.HashMap;

/**
 * A carnivorous dinosaur.
 *
 */
public class Allosaur extends Dinosaur {
	private HashMap<Stegosaur, Integer> cannotAttack = new HashMap<Stegosaur, Integer>();

	/**
	 * Constructor.
	 * All Allosaur are represented by an 'a' and have up to 100 hit points.
	 *
	 * @param name the name of this Stegosaur
	 */
	public Allosaur(String name, Boolean isBaby) {
		super(name, 'a', 100);
		setMaxUnconsciousTurns(20);
		setHungerThreshold(70);
		setBreedThreshold(50);
		if (isBaby) {
			this.setAge(0);
			this.hitPoints = 20;
		}
		else {
			this.setAge(50);
			this.hitPoints = 50;
		}
	}

	public HashMap<Stegosaur, Integer> getCannotAttack() {
		return cannotAttack;
	}

	public void setCannotAttack(HashMap<Stegosaur, Integer> cannotAttack) {
		this.cannotAttack = cannotAttack;
	}

	/**
	 * 
	 * @see Actor#playTurn(Actions, Action, GameMap, Display)
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		for (Stegosaur key : cannotAttack.keySet()) {
			int turnsRemaining = cannotAttack.get(key);
			if (turnsRemaining == 0) {
				cannotAttack.remove(key);
			} else {
				cannotAttack.put(key, turnsRemaining - 1);
			}
		}
		return super.playTurn(actions, lastAction, map, display);
	}
}
