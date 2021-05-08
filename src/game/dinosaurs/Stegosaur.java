package game.dinosaurs;

/**
 * A herbivorous dinosaur.
 *
 */
public class Stegosaur extends Dinosaur {
	/**
	 * Constructor.
	 * All Stegosaurs are represented by an 's' and have up to 100 hit points.
	 * 
	 * @param name the name of this Stegosaur
	 */

	public Stegosaur(String name, Boolean isBaby) {
		super(name, 's', 100);
		setMaxUnconsciousTurns(20);
		setHungerThreshold(90);
		setBreedThreshold(50);
		if (isBaby) {
			this.setAge(0);
			this.hitPoints = 10;
		}
		else {
			this.setAge(30);
			this.hitPoints = 50;
		}
	}
}
