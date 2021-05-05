package game;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
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
		//FIXME: Add check to see if on the edge of gameMap
		int bush_counter = 0;
		boolean tree_check = false;
		int x_coord = location.x();
		int y_coord = location.y();
		GameMap gameMap = location.map();

		if (gameMap.at(x_coord+1, y_coord).getGround() instanceof Bush){
			bush_counter ++;
		}
		if (gameMap.at(x_coord+1, y_coord).getGround() instanceof Tree){
			bush_counter ++;
		}
		if (gameMap.at(x_coord-1, y_coord).getGround() instanceof Bush){
			bush_counter ++;
		}
		if (gameMap.at(x_coord-1, y_coord).getGround() instanceof Tree){
			bush_counter ++;
		}
		if (gameMap.at(x_coord, y_coord+1).getGround() instanceof Bush){
			bush_counter ++;
		}
		if (gameMap.at(x_coord, y_coord+1).getGround() instanceof Tree){
			bush_counter ++;
		}
		if (gameMap.at(x_coord, y_coord-1).getGround() instanceof Bush){
			bush_counter ++;
		}
		Random random = new Random();
		int bushChance = random.nextInt(100);
		if (bush_counter >= 2 && 0 <= bushChance && bushChance <= 10){
			// change to bush
		}
		if (bush_counter < 2 && bushChance == 0){
			// change to bush
		}



		location
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
