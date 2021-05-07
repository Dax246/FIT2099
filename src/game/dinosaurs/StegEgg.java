package game.dinosaurs;

import edu.monash.fit2099.engine.Location;

public class StegEgg extends Egg{

    public StegEgg(){
        super("stegosaur egg", 'e', true);
        super.turnsUntilHatch = 10;
    }

    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        turnsUntilHatch -= 1;
        if (turnsUntilHatch == 0){
            currentLocation.addActor(new Allosaur("allosaur", true));
            currentLocation.removeItem(this);
        }
    }
}
