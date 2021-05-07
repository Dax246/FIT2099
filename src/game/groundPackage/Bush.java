package game.groundPackage;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.Fruit;
import game.behaviour_action.HarvestFruitAction;

import java.util.Random;

public class Bush extends Flora {

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

    //TODO: Add to capabilities
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction){
        Actions actions = new Actions();
        if (getNumberOfFruit() > 0){
            actions.add(new HarvestFruitAction());
        }
        return actions;
    }


    private boolean addFruit(Location location){
        //TODO: Decide whether to put in a parent class as bush and tree both use this
        boolean isValid = false;
        if (location.getGround() instanceof Bush){
            location.addItem(new Fruit('B'));
            isValid = true;
        }
        return isValid;
    }

    @Override
    public Fruit harvestFruit(Location location){
        Fruit harvestedFruit = null;
        for (Item item : location.getItems()){
            if (item instanceof Fruit && ((Fruit) item).getStoredLocation() == 'B'){
                harvestedFruit = (Fruit) item;
                location.removeItem(item);
                break;
            }
        }
        return  harvestedFruit;
    }
}
