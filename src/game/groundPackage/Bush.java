package game.groundPackage;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.interfaces.GroundInterface;
import game.Fruit;
import game.behaviour_action.HarvestFruitAction;
import game.dinosaurs.Brachiosaur;
import java.util.Random;

/**
 * @author Allan Chan and Damien Ambegoda
 * @version 1.0.0
 * @see Flora
 * A class that can produce fruit.
 */
public class Bush extends Flora implements GroundInterface {
    /**
     * Constructor
     */
    public Bush() {
        super('b');
    }

    /**
     * Method to produce fruit by chance
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location){
        resThirstyDinos(location);
        super.tick(location);
        if (location.getActor() instanceof Brachiosaur) {
            location.setGround(new Dirt());
        } else {
            Random random = new Random();
            int fruitChance = random.nextInt(100);
            if (fruitChance <= 10){
                boolean addedFruitValidly = addFruit(location);
                assert addedFruitValidly : "Bush can only add fruit to a bush";
            }
        }
    }

    /**
     * Actions that players can use on it
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return Actions that players can use on it
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction){
        Actions actions = new Actions();
        if (getNumberOfFruit() > 0){
            actions.add(new HarvestFruitAction());
        }
        return actions;
    }

    /**
     * Aads fruit to location item list
     * @param location to add fruit
     * @return boolean if adding fruit was successful
     */
    private boolean addFruit(Location location){
        boolean isValid = false;
        if (location.getGround() instanceof Bush){
            location.addItem(new Fruit('B'));
            isValid = true;
        }
        return isValid;
    }

    /**
     * Removes fruit from location list
     * @param location to remove fruit from
     * @return boolean if removing fruit was successful
     */
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
        return harvestedFruit;
    }
}
