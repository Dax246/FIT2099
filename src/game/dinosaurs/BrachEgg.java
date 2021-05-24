package game.dinosaurs;

import edu.monash.fit2099.engine.Location;
import game.EcoPoints;

/**
 * @author Allan Chan and Damien Ambegoda
 * @version 2.0.0
 * @see Egg
 * A class that hatches to an Brachiosaur.
 */
public class BrachEgg extends Egg{
    /**
     * Constructor
     */
    public BrachEgg(){
        super("Brachiosaur egg", 'e', true);
        super.turnsUntilHatch = 30;

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
                currentLocation.addActor(new Brachiosaur("Brachiosaur", true));
                currentLocation.removeItem(this);
                EcoPoints.increaseEcoPoints(EcoPoints.getGainEcoPoints().get("Brachiosaur hatches"));
                System.out.println("Brach egg hatched at (" + currentLocation.x() + ", " + currentLocation.y() + ")");
            }
        }
    }
}
