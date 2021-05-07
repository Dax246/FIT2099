package game.groundPackage;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.EcoPoints;
import game.Fruit;
import game.behaviour_action.HarvestFruitAction;

import java.util.Random;

public class Tree extends Flora {
	private int age = 0;


	public Tree() {
		super('+');
	}

	@Override
	public void tick(Location location) {
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

	private boolean addFruit(Location location){
		boolean isValid = false;
		if (location.getGround() instanceof Tree){
			location.addItem(new Fruit('T'));
			isValid = true;
		}
		return isValid;
	}


	private void dropFruit(Location location){
		for (Item item : location.getItems()){
			if (item instanceof Fruit && ((Fruit) item).getStoredLocation() == 'T'){
				((Fruit) item).setStoredLocation('G');
				break;
			}
		}
	}

	@Override
	public Actions allowableActions(Actor actor, Location location, String direction){
		Actions actions = new Actions();
		if (super.getNumberOfFruit() > 0){
			actions.add(new HarvestFruitAction());
		}
		return actions;
	}

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
