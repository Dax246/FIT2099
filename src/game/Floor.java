package game;

import edu.monash.fit2099.engine.Ground;

/**
 * A class that represents the floor inside a building.
 */
public class Floor extends Ground {


	public Floor() {
		super('_');
	}

	@Override
	public int numberOfFruit(Ground ground) {
		return 0;
	}
}
