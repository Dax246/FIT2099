package game.groundPackage;

import edu.monash.fit2099.engine.*;
import game.Fruit;
import game.behaviour_action.HarvestFruitAction;
import game.dinosaurs.Brachiosaur;

import java.util.Random;

/**
 * @author Allan Chan and Damien Ambegoda
 * @version 1.0.0
 * @see Ground
 * A class that can holds water.
 */
public class Lake extends Ground {
    private int capacity = 25;
    /**
     * Constructor
     */
    public Lake() {
        super('~');
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Method to produce water by chance
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location){
        super.tick(location);
        //TODO: IF RAIN, ADD WATER TO CAPACITY
    }

    //TODO: BEGIN WITH 5 FISH IN LOCATION ITEMS
}
