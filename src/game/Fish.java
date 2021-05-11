package game;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.groundPackage.Lake;

import java.util.ArrayList;

/**
 * @author Allan Chan and Damien Ambegoda
 * @version 1.0.0
 * @see Item
 * The class for item in lakes with water
 */
public class Fish extends Item {
    /**
     * Constructor
     */
    public Fish() {
        super("Fish", '3', true);
    }

    /**
     * Remove all fish if water level is 0 in lake
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        if (((Lake) currentLocation.getGround()).getCapacity() == 0) {
            ArrayList<Item> itemsToRemove = new ArrayList<>(currentLocation.getItems());
            for (Item item : itemsToRemove) {
                currentLocation.removeItem(item);
            }
        }
    }
}
