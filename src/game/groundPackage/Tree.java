package game.groundPackage;

import edu.monash.fit2099.engine.*;
import edu.monash.fit2099.interfaces.GroundInterface;
import game.EcoPoints;
import game.Fruit;
import java.util.Random;

/**
 * @author Allan Chan and Damien Ambegoda
 * @version 1.0.0
 * @see Flora
 * A class that can produce fruit.
 */
public class Tree extends Ground implements Flora {
	int numberOfFruit = 0;

	/**
	 * Age
	 */
	private int age = 0;

	/**
	 * Constructor
	 */
	public Tree() {
		super('+');
	}

	public int getNumberOfFruit() {
		return numberOfFruit;
	}

	public void incrementNumberOfFruit() {
		numberOfFruit++;
	}

	public void decrementNumberOfFruit() {
		numberOfFruit--;
	}

	/**
	 * Method to produce fruit by chance
	 * @param location The location of the Ground
	 */
	@Override
	public void tick(Location location) {
		resThirstyDinos(location);
		super.tick(location);

		age++;
		if (age == 10)
			displayChar = 't';
		if (age == 20)
			displayChar = 'T';

		Random random = new Random();
		int fruitChance = random.nextInt(100);
		if (fruitChance <= 50){
			addFruit(location, 'T');
			EcoPoints.increaseEcoPoints(EcoPoints.getGainEcoPoints().get("Fruit produced by a tree"));
		}

		if (fruitChance <= 5){
			dropFruit(location);
		}
	}

	/**
	 * Method to drop fruit
	 * @param location to drop fruit
	 */
	private void dropFruit(Location location){
		for (Item item : location.getItems()){
			if (item instanceof Fruit && ((Fruit) item).getStoredLocation() == 'T'){
				((Fruit) item).setStoredLocation('G');
				break;
			}
		}
	}

//	/**
//	 * Adds ability for players to interact with tree
//	 * @param actor the Actor acting
//	 * @param location the current Location
//	 * @param direction the direction of the Ground from the Actor
//	 * @return Actions that players can interact with tree
//	 */
	@Override
	public Actions allowableActions(Actor actor, Location location, String direction){
		return interfaceAllowableActions(location);
	}
//	@Override
//	public Actions allowableActions(Actor actor, Location location, String direction) {
//		return interfaceAllowableActions(actor, location);
//	}
}
