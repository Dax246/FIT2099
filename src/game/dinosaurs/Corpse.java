package game.dinosaurs;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

public class Corpse extends Item {
    private int corpseOf;
    private int rot_health;

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

    public int getCorpseOf() {
        return corpseOf;
    }

    @Override
    public void tick(Location currentLocation) {
        this.rot_health -= 1;
        if (this.rot_health == 0){
            currentLocation.removeItem(this);
        }
    }
}
