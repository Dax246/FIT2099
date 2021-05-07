package game.groundPackage;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.Fruit;

import java.util.ArrayList;
import java.util.List;

public abstract class Flora extends Ground {
    private int numberOfFruit = 0;


    public Flora(char displayChar) {
        super(displayChar);
    }

    public int getNumberOfFruit() { return numberOfFruit; }

    public Fruit harvestFruit(Location location) {return null;}
}
