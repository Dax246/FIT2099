package game;

public  class EcoPoints {
    static int ecoPoints = 0;

    static int getEcoPoints() {
        return ecoPoints;
    }

    static void increaseEcoPoints(int increaseAmount) {
        ecoPoints += increaseAmount;
    }

    static void decreaseEcoPoints(int decreaseAmount) {
        ecoPoints -= decreaseAmount;
    }
}
