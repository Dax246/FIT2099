package game.groundPackage;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.interfaces.GroundInterface;
import game.EcoPoints;
import game.Fruit;
import game.behaviour_action.HarvestFruitAction;
import java.util.Random;

/**
 * @author Allan Chan and Damien Ambegoda
 * @version 1.0.0
 * @see Flora
 * A class that can produce fruit.
 */
public class Tree extends Flora implements GroundInterface {
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
			boolean addedFruitValidly = addFruit(location);
			assert addedFruitValidly : "Tree can only add fruit to a tree";
			EcoPoints.increaseEcoPoints(1);
		}

		if (fruitChance <= 5){
			dropFruit(location);
		}
	}

	/**
	 * Adds fruit to location
	 * @param location to add fruit
	 * @return boolean if adding fruit was successful
	 */
	private boolean addFruit(Location location){
		boolean isValid = false;
		if (location.getGround() instanceof Tree){
			location.addItem(new Fruit('T'));
			isValid = true;
		}
		return isValid;
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

	/**
	 * Adds ability for players to interact with tree
	 * @param actor the Actor acting
	 * @param location the current Location
	 * @param direction the direction of the Ground from the Actor
	 * @return Actions that players can interact with tree
	 */
	@Override
	public Actions allowableActions(Actor actor, Location location, String direction){
		Actions actions = new Actions();
		if (super.getNumberOfFruit() > 0){
			actions.add(new HarvestFruitAction());
		}
		return actions;
	}

	/**
	 * Method to remove fruit from tree
	 * @param location to remove fruit from
	 * @return fruit removed
	 */
	@Override
	public Fruit harvestFruit(Location location){
		Fruit harvestedFruit = null;
		for (Item item : location.getItems()){
			if (item instanceof Fruit && ((Fruit) item).getStoredLocation() == 'T'){
				harvestedFruit = (Fruit) item;
				location.removeItem(item);
				break;
			}
		}
		return harvestedFruit;
	}
}
