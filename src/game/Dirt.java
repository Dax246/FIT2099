package game;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * A class that represents bare dirt.
 */
public class Dirt extends Ground {

	public Dirt() {
		super('.');
	}

	@Override
	public void tick(Location location) {

	}

	//tick(Location):
	// for exit in location.getExits():
	// tree = 1, bush = 2, other = 5
	// next to tree, chance = 0
	// elif next to 2 bushes, chance = 10
	// else chance = 1
	// newB = new Bush('b')
	// newB.items = copyItems(this.items)
	// this.location.setGround(new Bush('b'))


}
