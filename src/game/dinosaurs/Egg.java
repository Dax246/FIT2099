package game.dinosaurs;

import edu.monash.fit2099.engine.Item;

public abstract class Egg extends Item {
    int turnsUntilHatch;

    public Egg(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }


}
