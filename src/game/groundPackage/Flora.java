package game.groundPackage;

import edu.monash.fit2099.engine.Ground;
import game.Fruit;

import java.util.ArrayList;
import java.util.List;

public abstract class Flora extends Ground {
    private List<Fruit> grownFruits = new ArrayList<>();


    public Flora(char displayChar) {
        super(displayChar);
    }

    public int numberOfFruit() {
        return grownFruits.size();
    }

    public List<Fruit> getGrownFruits() {
        return grownFruits;
    }

    public Fruit getFruit() {
        Fruit fruit = getGrownFruits().get(0);
        getGrownFruits().remove(0);
        return fruit;
    }
}
