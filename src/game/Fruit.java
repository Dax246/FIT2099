package game;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.PickUpItemAction;
import game.groundPackage.Flora;

/**
 * @author Allan Chan and Damien Ambegoda
 * @version 2.0.0
 * @see Item
 * The class for item that flora produce
 */
public class Fruit extends Item {
    /**
     * Ticks until fruit rot
     */
    private int rot_health = 15;

    /**
     * Char to represent where fruit is
     */
    private char storedLocation; // 'G' if on the Ground, 'B' if in a Bush, 'T' if in a tree

    /**
     * Constructor
     * @param storedLocation Char to represent where fruit is
     */
    public Fruit(char storedLocation) {
        super("Fruit", 'f', true);
        this.storedLocation = storedLocation;
    }

    /**
     * Ticks fruit rot counter
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        rot_health -= 1;
        if (rot_health == 0){
            currentLocation.removeItem(this);
            if (currentLocation.getGround() instanceof Flora) {
                ((Flora) currentLocation.getGround()).decrementNumberOfFruit();
            }
        }
    }

    /**
     * Determimes whether the fruit can be picked up
     * @return PickUpItemAction is fruit can be picked up
     */
    @Override
    public PickUpItemAction getPickUpAction() {
        if (storedLocation == 'G' || storedLocation == 'H') {
            return super.getPickUpAction();
        }
        else {
            return null;
        }
    }

    public char getStoredLocation() {
        return storedLocation;
    }

    public void setStoredLocation(char storedLocation) {
        this.storedLocation = storedLocation;
    }
}
