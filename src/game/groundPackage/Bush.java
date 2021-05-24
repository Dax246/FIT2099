package game.groundPackage;

import edu.monash.fit2099.engine.*;
import edu.monash.fit2099.interfaces.GroundInterface;
import game.dinosaurs.Brachiosaur;
import java.util.Random;

/**
 * @author Allan Chan and Damien Ambegoda
 * @version 1.0.0
 * @see Flora
 * A class that can produce fruit.
 */
public class Bush extends Ground implements Flora {
    /**
     * Number of fruit in bush
     */
    private int numberOfFruit = 0;

    /**
     * Constructor
     */
    public Bush() {
        super('b');
    }

    public int getNumberOfFruit() {
        return numberOfFruit;
    }

    public void incrementNumberOfFruit() {
        numberOfFruit++;
    }

    public void decrementNumberOfFruit() {
        numberOfFruit--;
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
                addFruit(location, 'B');
            }
        }
    }

    /**
     * Allowable actions on bush
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return allowable actions on this ground
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction){
        return interfaceAllowableActions(location);
    }

}
