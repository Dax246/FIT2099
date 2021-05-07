package game;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

public class Fruit extends Item {
    private int rot_health = 15;

    public Fruit() {
        super("fruit", 'f', true);
    }

    @Override
    public void tick(Location currentLocation) {
        rot_health -= 1;
        if (rot_health == 0){
            currentLocation.removeItem(this);
        }
    }
}
