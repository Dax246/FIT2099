package game.groundPackage;

import edu.monash.fit2099.engine.*;
import game.Fish;
import game.Fruit;
import game.behaviour_action.HarvestFruitAction;
import game.dinosaurs.Brachiosaur;
import game.dinosaurs.Pterodactyl;

import java.util.Random;

/**
 * @author Allan Chan and Damien Ambegoda
 * @version 1.0.0
 * @see Ground
 * A class that can holds water.
 */
public class Lake extends Ground {
    private int capacity = 25;
    private boolean initialisedFish = false;
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
        if (!initialisedFish) {
            for (int i = 0; i < 5; i++) {
                location.addItem(new Fish());
            }
        }
        super.tick(location);

        //TODO: IF MAP RAINED, ADD RANDOM AMOUNT OF WATER TO CAPACITY
    }


    @Override
    public boolean canActorEnter(Actor actor) {
        return actor instanceof Pterodactyl;
    }
}
