package game;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tree extends Ground {
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

	private void dropFruit(Location location){
		Fruit fruit = grownFruits.get(0);
		grownFruits.remove(0);
		location.addItem(fruit);
	}
}
