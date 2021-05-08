package game.groundPackage;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;

/**
 * @author Allan Chan and Damien Ambegoda
 * @version 1.0.0
 * @see Ground
 * A class that players interact with to puchase items
 */
public class Wall extends Ground {

	/**
	 * Constructor
	 */
	public Wall() {
		super('#');
	}

	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}
	
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
}
