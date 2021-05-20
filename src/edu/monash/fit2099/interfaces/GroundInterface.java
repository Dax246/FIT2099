package edu.monash.fit2099.interfaces;

import edu.monash.fit2099.engine.Location;
import game.dinosaurs.Dinosaur;

/**
 * This interface provides the ability to add methods to Ground, without modifying code in the engine,
 * or downcasting references in the game.   
 */

public interface GroundInterface {
    default void resThirstyDinos(Location location) {
        if (location.containsAnActor() && location.getActor() instanceof Dinosaur) {
            Dinosaur dinoActor = (Dinosaur) location.getActor();
            if (dinoActor.isUnconsciousDueToThirst()) {
                dinoActor.quench(10);
            }
        }
    }
}
