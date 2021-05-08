package game;

import edu.monash.fit2099.engine.Item;

/**
 * @author Allan Chan and Damien Ambegoda
 * @version 1.0.0
 * @see Item
 * The class for item that Stegosaurs/Brachiosaurs can be fed
 */
public class VegetarianMealKit extends Item {
    /**
     * Constructor
     */
    public VegetarianMealKit(){
        super("vegetarian meal kit", 'v', true);
    }
}
