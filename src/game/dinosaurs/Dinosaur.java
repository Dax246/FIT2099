package game.dinosaurs;

import edu.monash.fit2099.engine.*;
import game.behaviour_action.*;
import game.Player;
import java.util.Random;

/**
 * @author Allan Chan and Damien Ambegoda
 * @version 1.0.0
 * @see Actor
 * A class that contains the shared methods/attributes for all dinosaurs.
 */
public abstract class Dinosaur extends Actor{
    /**
     * Age of dinosaur
     */
    private int age;
    /**
     * Sex of dinosaur
     */
    private Character sex;
    /**
     * Turns until egg is laid
     */
    private int layEggCounter = 0;
    /**
     * Number of turns until a dinosaur dies
     */
    private int maxUnconsciousTurns;
    /**
     * Number of turns a dinosaur has been unconscious
     */
    private int unconsciousTurnsCounter;
    /**
     * Level of hitpoints that it becomes hungry
     */
    private int hungerThreshold;
    /**
     * Level of hitpoints it won't breed below
     */
    private int breedThreshold;

    private int waterLevel = 60;

    private int maxWaterLevel;

    private boolean unconsciousDueToThirst = false;

    /**
     * Constructor
     * @param name Name of Dinosaur
     * @param displayChar Char to represent dinosaur in output
     * @param maxHitPoints max hitpoints of dinosaur
     */
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

    public void setMaxWaterLevel(int maxWaterLevel) {
        this.maxWaterLevel = maxWaterLevel;
    }

    public int getUnconsciousTurnsCounter() {
        return unconsciousTurnsCounter;
    }

    public int getMaxUnconsciousTurns() {
        return maxUnconsciousTurns;
    }

    public void setSex(Character sex) {
        this.sex = sex;
    }

    /**
     * Returns action to execute this turn.
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return Action to execute this turn.
     */
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
                FindFoodBehaviour findFoodBehaviour = new FindFoodBehaviour();
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
            MoveToPlayerBehaviour moveToPlayerBehaviour = new MoveToPlayerBehaviour();
            Action nextAction = moveToPlayerBehaviour.getAction(this, map);
            if (nextAction != null) {
                return nextAction;
            } else {
                return (new WanderBehaviour()).getAction(this, map);
            }
        }
    }

    /**
     * Interactions players can have with dinosaurs
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return Actions that players can have with dinosaurs
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();
        if (otherActor instanceof Player){
            actions.add(new FeedDinoAction(this));
            actions.add(new AttackAction(this));
        }
        return actions;
    }

    public int getMaxHitPoints(){
        return maxHitPoints;
    }

    public Character getSex() {
        return sex;
    }

    /**
     * Determines if dinosaur is adult based on age
     * @return boolean that's true if dinosaur is an adult
     */
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
}
