package game;

import java.util.HashMap;

/**
 * @author Allan Chan and Damien Ambegoda
 * @version 1.0.0
 * The class for modifying vending machine currency.
 */
public  class EcoPoints {
    /**
     * Balance
     */
    static int ecoPoints = 0;

    static HashMap<String, Integer> gainEcoPoints = new HashMap<String, Integer>(){{
        put("Fruit produced by a tree", 1);
        put("Fruit harvested", 10);
        put("Fruit fed to dinosaur", 10);
        put("Stegosaur hatches", 100);
        put("Allosaur hatches", 1000);
        put("Brachiosaur hatches", 1000);
        put("Pterodactyl hatches", 100);

    }};

    static HashMap<String, Integer> spendEcoPoints = new HashMap<String, Integer>(){{
        put("Fruit", 30);
        put("Vegetarian Meal Kit", 100);
        put("Carnivore Meal Kit", 500);
        put("Stegosaur Egg", 200);
        put("Peterodactyl Egg", 200);
        put("Brachiosaur Egg", 500);
        put("Allosaur Egg", 1000);
        put("Laser Gun", 500);

    }};

    public static HashMap<String, Integer> getGainEcoPoints() {
        return gainEcoPoints;
    }

    public static HashMap<String, Integer> getSpendEcoPoints() {
        return spendEcoPoints;
    }

    public static int getEcoPoints() {
        return ecoPoints;
    }

    public static void increaseEcoPoints(int increaseAmount) {
        ecoPoints += increaseAmount;
    }

    public static void decreaseEcoPoints(int decreaseAmount) {
        ecoPoints -= decreaseAmount;
    }
}
