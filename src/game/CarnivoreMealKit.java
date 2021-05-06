package game;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;

import java.util.ArrayList;
import java.util.List;

public class CarnivoreMealKit extends Item {
    public CarnivoreMealKit(){
        super("carnivore meal kit", 'C', true);
    }

    public abstract static class Flora extends Ground {
        private List<Fruit> grownFruits = new ArrayList<>();



        public Flora(char displayChar) {
            super(displayChar);
        }

        public int numberOfFruit(){
            return grownFruits.size();
        };

        public List<Fruit> getGrownFruits() {
            return grownFruits;
        }

        public Fruit getFruit(){
            Fruit fruit = getGrownFruits().get(0);
            getGrownFruits().remove(0);
            return fruit;
        }
    }
}
