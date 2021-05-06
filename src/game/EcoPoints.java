package game;

public class EcoPoints {
    private int ecoPoints = 0;

    public int getEcoPoints() {
        return ecoPoints;
    }

    public void increaseEcoPoints(int increaseAmount) {
        ecoPoints += increaseAmount;
    }

    public void decreaseEcoPoints(int decreaseAmount) {
        ecoPoints -= decreaseAmount;
    }
}
