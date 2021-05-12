package game.dinosaurs;

import edu.monash.fit2099.engine.Location;
import game.EcoPoints;

/**
 * @author Allan Chan and Damien Ambegoda
 * @version 1.0.0
 * @see Egg
 * A class that hatches to an Pterodactyl.
 */
public class PteroEgg extends Egg{
    /**
     * Constructor
     */
    public PteroEgg(){
        super("Pterodactyl egg", 'e', true);
        super.turnsUntilHatch = 10;
    }

    /**
     * Hatches egg if location has no actor and turns until hatch = 0
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        turnsUntilHatch -= 1;
        if (turnsUntilHatch == 0){
            if (currentLocation.containsAnActor()) {
                turnsUntilHatch += 1;
            } else {
                currentLocation.addActor(new Stegosaur("Pterodactyl", true));
                currentLocation.removeItem(this);
                EcoPoints.increaseEcoPoints(100);
                System.out.println("Ptero egg hatched at (" + currentLocation.x() + ", " + currentLocation.y() + ")");
            }
        }
    }
}
