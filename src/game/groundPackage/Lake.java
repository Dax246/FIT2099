package game.groundPackage;

import edu.monash.fit2099.engine.*;
import game.Fish;
import game.Util;
import game.dinosaurs.Pterodactyl;

import java.util.Random;

/**
 * @author Allan Chan and Damien Ambegoda
 * @version 1.0.0
 * @see Ground
 * A class that can holds water.
 */
public class Lake extends Ground {
    private int capacity = 25;
    private boolean initialisedFish = false;
    private int rainCounter = 0;
    /**
     * Constructor
     */
    public Lake() {
        super('~');
    }

    public int getCapacity() {
        return capacity;
    }

    public void sip() {
        capacity -= 1;
    }

    /**
     * Method to produce water by chance
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location){
        if (!initialisedFish) {
            for (int i = 0; i < 5; i++) {
                location.addItem(new Fish());
            }
            initialisedFish = true;
        }

        Random random = new Random();
        // Rain tick
        if (Util.rainThisTick) {
            int intRainFall = random.nextInt(60);
            while (intRainFall < 10) {
                intRainFall = random.nextInt(60);
            }
            double doubleRainFall = intRainFall / 100.0;
            int rainFallAmount = (int) Math.floor(doubleRainFall * 20);
            capacity += rainFallAmount;
        }

        // Fish tick
        if (random.nextInt(100) < 60){
            location.addItem(new Fish());
        }

        super.tick(location);
    }


    @Override
    public boolean canActorEnter(Actor actor) {
        return (actor instanceof Pterodactyl && ((Pterodactyl) actor).isFlying());
    }
}
