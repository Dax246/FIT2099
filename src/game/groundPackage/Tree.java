package game.groundPackage;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;
import game.EcoPoints;
import game.Flora;
import game.Fruit;
import game.behaviour_action.PickFruitAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tree extends Flora {
	private int age = 0;
	protected List<Fruit> grownFruits = new ArrayList<>();


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
			((Tree) location.getGround()).grownFruits.add(new Fruit());
			isValid = true;
		}
		return isValid;
	}

	@Override
	public int numberOfFruit() {
		return getGrownFruits().size();
	}

	private void dropFruit(Location location){
		Fruit fruit = grownFruits.get(0);
		grownFruits.remove(0);
		location.addItem(fruit);
	}

	@Override
	public Actions allowableActions(Actor actor, Location location, String direction){
		Actions actions = new Actions();
		if (getGrownFruits().size() > 0){
			actions.add(new PickFruitAction());
		}
		return actions;
	}
}
