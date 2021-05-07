package game.dinosaurs;

import edu.monash.fit2099.engine.Location;
import game.EcoPoints;

public class BrachEgg extends Egg{

    public BrachEgg(){
        super("brachiosaur egg", 'e', true);
        super.turnsUntilHatch = 10;

    }

    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        turnsUntilHatch -= 1;
        if (turnsUntilHatch == 0){
            currentLocation.addActor(new Brachiosaur("brachiosaur", true));
            currentLocation.removeItem(this);
            EcoPoints.increaseEcoPoints(1000);
        }
    }
}
