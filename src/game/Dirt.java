package game;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.NumberRange;

import java.util.Random;

/**
 * A class that represents bare dirt.
 */
public class Dirt extends Ground {

	public Dirt() {
		super('.');
	}

	@Override
	public void tick(Location location) {

		NumberRange xRange = location.map().getXRange();
		NumberRange yRange = location.map().getYRange();

		int bush_counter = 0;
		boolean tree_check = false;
		int x_coord = location.x();
		int y_coord = location.y();
		GameMap gameMap = location.map();

		if (xRange.max() >= x_coord+1) {
			if (gameMap.at(x_coord + 1, y_coord).getGround() instanceof Bush) {
				bush_counter++;
			}
			if (gameMap.at(x_coord + 1, y_coord).getGround() instanceof Tree) {
				tree_check = true;
			}
		}
		if (xRange.min() <= x_coord - 1) {
			if (gameMap.at(x_coord - 1, y_coord).getGround() instanceof Bush) {
				bush_counter++;
			}
			if (gameMap.at(x_coord - 1, y_coord).getGround() instanceof Tree) {
				tree_check = true;
			}
		}
		if (yRange.max() >= y_coord+1) {
			if (gameMap.at(x_coord, y_coord + 1).getGround() instanceof Bush) {
				bush_counter++;
			}
			if (gameMap.at(x_coord, y_coord + 1).getGround() instanceof Tree) {
				tree_check = true;
			}
		}
		if (yRange.min() <= y_coord-1) {
			if (gameMap.at(x_coord, y_coord - 1).getGround() instanceof Bush) {
				bush_counter++;
			}
			if (gameMap.at(x_coord, y_coord - 1).getGround() instanceof Tree) {
				tree_check = true;
			}
		}
		Random random = new Random();
		int bushChance = random.nextInt(100);
		if (!tree_check) {
			if (bush_counter >= 2 && bushChance <= 10) {
				location.setGround(new Bush());
			}
			if (bush_counter < 2 && bushChance == 0) {
				location.setGround(new Bush());
			}
		}


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
