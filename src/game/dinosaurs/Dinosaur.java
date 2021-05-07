package game.dinosaurs;

import edu.monash.fit2099.engine.*;

public abstract class Dinosaur extends Actor {
    private int age;

    public Dinosaur(String name, char displayChar, int maxHitPoints) {
        super(name, displayChar, maxHitPoints);
    }

    public Dinosaur(String name, char displayChar, int maxHitPoints, int hitPoints) {
        super(name, displayChar, maxHitPoints);
        this.hitPoints = hitPoints;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        this.hitPoints -= 1;
        return new DoNothingAction();
    }
}
