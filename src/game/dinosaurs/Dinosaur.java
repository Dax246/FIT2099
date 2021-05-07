package game.dinosaurs;

import edu.monash.fit2099.engine.*;
import game.behaviour_action.AttackAction;
import game.Player;
import game.behaviour_action.FeedDinoAction;

public abstract class Dinosaur extends Actor{
    private int age;

    //TODO: Make sure hatch egg is top of priority is egg is ready to be layed
    //TODO: Construct dinosaurs with Sex

    // 'M' for Male and 'F' for female
    private Character sex;
    private int layEggCounter = 0;




    public Dinosaur(String name, char displayChar, int maxHitPoints) {
        super(name, displayChar, maxHitPoints);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void incrementAge(int incrementAmount){
        this.age += incrementAmount;
    }

    public int getLayEggCounter() {
        return layEggCounter;
    }

    public void setLayEggCounter(int layEggCounter) {
        this.layEggCounter = layEggCounter;
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        this.hitPoints -= 1;
        if (layEggCounter > 0){
            layEggCounter -= 1;
            // If layEggCounter == 0, lay egg action
        }
        return new DoNothingAction();
    }

    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();
        if (otherActor instanceof Player){
            actions.add(new FeedDinoAction(this));
            actions.add(new AttackAction(this));
        }
        return actions;
    }

    public int getHitPoints(){
        return hitPoints;
    }

    public int getMaxHitPoints(){
        return maxHitPoints;
    }

    public Character getSex() {
        return sex;
    }
}
