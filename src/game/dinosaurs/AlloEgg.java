package game.dinosaurs;

import edu.monash.fit2099.engine.Location;
import game.EcoPoints;

public class AlloEgg extends Egg{

    public AlloEgg(){
        super("Allosaur egg", 'e', true);
        super.turnsUntilHatch = 50;
    }

    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        turnsUntilHatch -= 1;
        if (turnsUntilHatch == 0){
            if (currentLocation.containsAnActor()) {
                turnsUntilHatch += 1;
            } else {
                currentLocation.addActor(new Allosaur("Allosaur", true));
                currentLocation.removeItem(this);
                EcoPoints.increaseEcoPoints(1000);
                System.out.println("Allo egg hatched at (" + currentLocation.x() + ", " + currentLocation.y() + ")");
            }
        }
    }
}
