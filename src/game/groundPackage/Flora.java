package game.groundPackage;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.Fruit;
import game.behaviour_action.HarvestFruitAction;

/**
 * @author Allan Chan and Damien Ambegoda
 * @version 1.0.0
 * @see Flora
 * A parent class of grounds that can produce fruit.
 */
public interface Flora {
    int getNumberOfFruit();
    void incrementNumberOfFruit();
    void decrementNumberOfFruit();

    /**
     * Aads fruit to location item list
     * @param location to add fruit
     */
    default void addFruit(Location location, Character storedLocation) {
        location.addItem(new Fruit(storedLocation));
        incrementNumberOfFruit();
    }

    default boolean harvestFruit(Location location, Character storedLocation) {
        boolean foundFruit = false;
        for (Item item : location.getItems()){
            if (item instanceof Fruit && (((Fruit) item).getStoredLocation() == storedLocation)){
                foundFruit = true;
                location.removeItem(item);
                decrementNumberOfFruit();
                break;
            }
        }
        return foundFruit;
    }

    default Actions interfaceAllowableActions(Location location){
        Actions actions = new Actions();
        if (getNumberOfFruit() > 0){
            actions.add(new HarvestFruitAction(location));
        }
        return actions;
    }
}