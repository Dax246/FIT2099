package game;

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
