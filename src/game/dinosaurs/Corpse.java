package game.dinosaurs;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

/**
 * @author Allan Chan and Damien Ambegoda
 * @version 1.0.0
 * @see Dinosaur
 * Corpse of dead dinosaur
 */
public class Corpse extends Item {
    /**
     * int to differentiate between different dinosaurs
     */
    private int corpseOf;
    /**
     * Turns until corpse rots and is removed from map.
     */
    private int rot_health;

    /**
     * Constructor
     * @param corpseOf int to differentiate between different dinosaurs
     */
    public Corpse(int corpseOf) {
        super("Corpse", 'c', false);
        this.corpseOf = corpseOf;  //1 for steg, 2 for brach, 3 for allo
        if (corpseOf == 1 || corpseOf == 3) {
            this.rot_health = 20;
        }
        else {
            this.rot_health = 40;
        }
    }

    /**
     * Gets int of corpse
     * @return int to differentiate corpse
     */
    public int getCorpseOf() {
        return corpseOf;
    }

    /**
     * Rots the corpse each turn
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        this.rot_health -= 1;
        if (this.rot_health == 0){
            currentLocation.removeItem(this);
        }
    }
}
