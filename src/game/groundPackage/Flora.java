package game.groundPackage;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.Fruit;

/**
 * @author Allan Chan and Damien Ambegoda
 * @version 1.0.0
 * @see Flora
 * A parent class of grounds that can produce fruit.
 */
public abstract class Flora extends Ground {
    /**
     * Attribute to store number of fruit currently in location
     */
    private int numberOfFruit = 0;

    /**
     * Constructor
     * @param displayChar Char to represent Flora in output
     */
    public Flora(char displayChar) {
        super(displayChar);
    }

    public int getNumberOfFruit() { return numberOfFruit; }

    public Fruit harvestFruit(Location location) {return null;}
}
