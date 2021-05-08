package game.dinosaurs;

import edu.monash.fit2099.engine.*;
import game.Behaviour;
import game.behaviour_action.*;
import game.Player;

import java.util.Random;

public abstract class Dinosaur extends Actor{
    private int age;

    //TODO: Make sure hatch egg is top of priority is egg is ready to be layed
    //TODO: Construct dinosaurs with Sex

    // 'M' for Male and 'F' for female
    private Character sex;
    private int layEggCounter = 0;
    private int maxUnconsciousTurns;
    private int unconsciousTurnsCounter;
    private int hungerThreshold;
    private int breedThreshold;



    public Dinosaur(String name, char displayChar, int maxHitPoints) {
        super(name, displayChar, maxHitPoints);

        //assign random sex
        Random sexRandomiser = new Random();
        int randomSex = sexRandomiser.nextInt(2);
        if (randomSex == 0) {
            sex = 'M';
        }
        else {
            sex = 'F';
        }

        unconsciousTurnsCounter = 0;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHungerThreshold() {
        return hungerThreshold;
    }

    public void setHungerThreshold(int hunger) {
        hungerThreshold = hunger;
    }

    public void setBreedThreshold(int breedThreshold) {
        this.breedThreshold = breedThreshold;
    }

    public int getLayEggCounter() {
        return layEggCounter;
    }

    public void setLayEggCounter(int layEggCounter) {
        this.layEggCounter = layEggCounter;
    }

    public void setMaxUnconsciousTurns(int turns) {
        maxUnconsciousTurns = turns;
    }

    public int getUnconsciousTurnsCounter() {
        return unconsciousTurnsCounter;
    }

    public int getMaxUnconsciousTurns() {
        return maxUnconsciousTurns;
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        this.age += 1;

        //lay egg if pregnant
        if (layEggCounter > 0) {
            layEggCounter -= 1;
            if (layEggCounter == 0) {
                return new LayEggAction();
            }
        }

        //increment unconscious counter
        if (!isConscious()) {
            unconsciousTurnsCounter += 1;
            if (unconsciousTurnsCounter == maxUnconsciousTurns) {
                return new DeathAction();
            } else if (unconsciousTurnsCounter > maxUnconsciousTurns) {
                throw new AssertionError("Dinosaur should have already died");
            }
            else {
                return new DoNothingAction();
            }
        } else {
            unconsciousTurnsCounter = 0;
            //becomes hungry
            if (hitPoints == hungerThreshold) {
                System.out.println(this.toString() + " at (" + map.locationOf(this).x() + ", " + map.locationOf(this).y() + ") is getting hungry! ");
            }
            this.hitPoints -= 1;

            // fell unconscious due to hunger
            if (!isConscious()) {
                return new DoNothingAction();
            }

            //mate if adjacent to mate
            BreedBehaviour breedBehaviour = new BreedBehaviour();
            Action breedBehaviourNextAction = breedBehaviour.getAction(this, map);
            if (breedBehaviourNextAction instanceof BreedAction) {
                if (hitPoints >= breedThreshold) {
                    return breedBehaviourNextAction;
                }
            }

            //otherwise consume/move towards food if hungry
            if (hitPoints < hungerThreshold) {
                Behaviour findFoodBehaviour = new FindFoodBehaviour();
                Action nextAction = findFoodBehaviour.getAction(this, map);
                if (nextAction != null) {
                    return nextAction;
                }
            } else { //move towards mate since not hungry
                if (breedBehaviourNextAction instanceof MoveActorAction) {
                    return breedBehaviourNextAction;
                }
            }

            //not hungry and no mate so move towards player
            Behaviour moveToPlayerBehaviour = new MoveToPlayerBehaviour();
            return moveToPlayerBehaviour.getAction(this, map);
        }
    }

        //increment age
        //if unconscious, increment unconsciousTurnCounter
        //		//if unconsciousTurnCounter > maxTurnsDeath: return new DeathAction()
        //
        //		//decrement hitpoints
        //if hungry, print message
        //			//if hitpoints == 0: unconscious = true, Return DoNothingAction
        //
        //		//if next to mate and hitPoints > minHitPointsBreeding
        //			//Return breedAction
        //
        //
        //		//else if steg/brach and next to fruit or allo and next to steg/allo
        //			//Return eatFruitAction
        //		//else if food level > 90 (140 for brachiosaur)
        //			//Return findMateBehaviour.getAction
        //		//else
        //			//Return findFruitBehaviour.getAction if not allosaur
        //			//Return findFoodBehaviour.getAction if allosaur (corpse, egg, steg)
        // if no suitable moves, move towards player

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

    public boolean isAdult() {
        if (this.name == "Stegosaur") {
            if (age >= 30) {
                return true;
            }
            else {
                return false;
            }
        }
        else if (this.name == "Brachiosaur" || this.name == "Allosaur") {
            if (this.age >= 50) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            throw new AssertionError("Unexpected Dinosaur");
        }
    };

    private int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }
}
