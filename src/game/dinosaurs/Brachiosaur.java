package game.dinosaurs;

/**
 * A herbivorous dinosaur.
 *
 */
public class Brachiosaur extends Dinosaur {
	/**
	 * Constructor.
	 * All Brachiosaur are represented by a 'd' and have up to 160 hit points.
	 *
	 * @param name the name of this Brachiosaur
	 */

	public Brachiosaur(String name, Boolean isBaby) {
		super(name, 'd', 160);
		setMaxUnconsciousTurns(15);
		setHungerThreshold(140);
		setBreedThreshold(70);
		if (isBaby) {
			this.setAge(0);
			this.hitPoints = 10;
		}
		else {
			this.setAge(50);
			this.hitPoints = 100;
		}
	}
}
