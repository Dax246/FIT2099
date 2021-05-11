package game.dinosaurs;

/**
 * @author Allan Chan and Damien Ambegoda
 * @version 1.0.0
 * @see Dinosaur
 * A herbivorous dinosaur.
 */
public class Stegosaur extends Dinosaur {
	/**
	 * Constructor.
	 * All Stegosaurs are represented by an 's' and have up to 100 hit points.
	 * @param name the name of this Stegosaur
	 * @param isBaby boolean that's true if Stegosaur to create is a baby.
	 */
	public Stegosaur(String name, Boolean isBaby) {
		super(name, 's', 100);
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
}
