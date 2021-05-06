package game;

public  class EcoPoints {
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
