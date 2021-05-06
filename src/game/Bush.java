package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bush extends Ground {
    private List<Fruit> grownFruits = new ArrayList<>();

    public Bush() {
        super('b');
    }



    @Override
    public void tick(Location location){
        super.tick(location);

        Random random = new Random();
        int fruitChance = random.nextInt(100);
        if (fruitChance <= 10){
            boolean addedFruitValidly = addFruit(location);
            assert addedFruitValidly : "Bush can only add fruit to a bush";
        }

    }

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction){
        Actions actions = new Actions();

        return actions;
    }

    @Override
    public int numberOfFruit(Ground ground) {
        return grownFruits.size();
    }

    private boolean addFruit(Location location){
        //TODO: Decide whether to put in a parent class as bush and tree both use this
        boolean isValid = false;
        if (location.getGround() instanceof Bush){
            ((Bush) location.getGround()).grownFruits.add(new Fruit());
            isValid = true;
        }
        return isValid;
    }
}
