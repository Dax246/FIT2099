package game.dinosaurs;

/**
 * @author Allan Chan and Damien Ambegoda
 * @version 1.0.0
 * @see Dinosaur
 * A herbivorous dinosaur.
 */
public class Brachiosaur extends Dinosaur implements DinoAge {
	/**
	 * Constructor.
	 * All Brachiosaur are represented by a 'd' and have up to 160 hit points.
	 * @param name the name of this Brachiosaur
	 * @param isBaby boolean that's true if Brachiosaur to create is a baby.
	 */
	public Brachiosaur(String name, Boolean isBaby) {
		super(name, 'd', 160);
		setMaxUnconsciousTurns(15);
		setHungerThreshold(140);
		setBreedThreshold(70);
		setMaxWaterLevel(200);
		if (isBaby) {
			this.setAge(0);
			this.hitPoints = 10;
		}
		else {
			this.setAge(50);
			this.hitPoints = 100;
		}
	}

	public boolean isAdult() {
		return getAge() >= 50;
	}
}
