package game.dinosaurs;

import edu.monash.fit2099.engine.Location;
import game.EcoPoints;

public class BrachEgg extends Egg{

    public BrachEgg(){
        super("Brachiosaur egg", 'e', true);
        super.turnsUntilHatch = 10;

    }

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
                EcoPoints.increaseEcoPoints(1000);
            }
        }
    }
}
