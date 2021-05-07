package game;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

public class Fruit extends Item {
    private int rot_health = 15;

    // 'G' if on the Ground, 'B' if in a Bush, 'T' if in a tree
    private char storedLocation;

    //TODO: Remove capability of fruit to be picked up if in Bush or Tree

    public Fruit(char storedLocation) {
        super("fruit", 'f', true);
        this.storedLocation = storedLocation;
    }

    @Override
    public void tick(Location currentLocation) {
        rot_health -= 1;
        if (rot_health == 0){
            currentLocation.removeItem(this);
        }
    }

    public char getStoredLocation() {
        return storedLocation;
    }

    public void setStoredLocation(char storedLocation) {
        this.storedLocation = storedLocation;
    }
}
