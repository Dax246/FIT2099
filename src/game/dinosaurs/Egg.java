package game.dinosaurs;

import edu.monash.fit2099.engine.Item;

/**
 * @author Allan Chan and Damien Ambegoda
 * @version 1.0.0
 * @see Item
 * A class that is extended by each dinosaur type's egg.
 */
public abstract class Egg extends Item {
    /**
     * Shared attribute for turns until egg is hatched.
     */
    int turnsUntilHatch;

    /**
     * Constructor
     * @param name Name of Egg
     * @param displayChar Char to represent Egg in output
     * @param portable Boolean for if egg can is portable (can be inventory)
     */
    public Egg(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }


}
