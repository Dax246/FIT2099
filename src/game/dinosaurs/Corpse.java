package game.dinosaurs;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

/**
 * @author Allan Chan and Damien Ambegoda
 * @version 2.0.0
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

    /**
     * Used to initialise the correct corpse based on what dinosaur died
     *
     * @param corpseInt integer representing the dino that died
     */
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

    /**
     * How much health to gain from eating the corpse
     *
     * @return integer value of health gained from eating corpse
     */
    public int getCorpseHeal() {
        return corpseHeal;
    }

    /**
     * Name of Dinosaur that will be hatched
     *
     * @return string name of dinosaur
     */
    public String getCorpseDinoName() {
        return corpseDinoName;
    }

    /**
     * Adjusts the remaining HP of corpse and how much is healed from eating corpse when Pterodactyl nibbles
     *
     */
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
