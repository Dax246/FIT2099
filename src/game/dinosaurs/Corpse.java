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
     * Turns until corpse rots and is removed from map.
     */
    private int rot_health;

    private int corpseHeal;

    private String corpseDinoName;

    /**
     * Constructor
     * @param corpseInt int to differentiate between different dinosaurs: 1 for steg, 2 for brach, 3 for allo, 4 for ptero
     */
    public Corpse(int corpseInt) {
        super("Corpse", 'c', false);
        initialiseCorpse(corpseInt);
        if (corpseInt == 2) {
            this.rot_health = 40;
        }
        else {
            this.rot_health = 20;
        }
    }

    private void initialiseCorpse(int corpseInt) {
        switch (corpseInt) {
            case 1 -> {
                corpseHeal = 50;
                corpseDinoName = "Stegosaur";
            }
            case 2 -> {
                Allosaur tempAllo = new Allosaur("Allosaur", false);
                corpseHeal = tempAllo.getMaxHitPoints();
                corpseDinoName = "Brachiosaur";
            }
            case 3 -> {
                corpseHeal = 50;
                corpseDinoName = "Allosaur";
            }
            case 4 -> {
                corpseHeal = 30;
                corpseDinoName = "Pterodactyl";
            }
        }
    }

    public int getCorpseHeal() {
        return corpseHeal;
    }

    public String getCorpseDinoName() {
        return corpseDinoName;
    }

    public void pteroNibble() {
        corpseHeal -= 10;
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
