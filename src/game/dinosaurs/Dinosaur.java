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
    private int unconsciousTurnsCounter = 0;
    private int unconsciousTurnsCounterThirst = 0;
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

    private final int maxUnconsciousTurnsDueToThirst = 15;

    private final int thirstThreshold = 40;

    private Location currentLocation;
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

    public void decrementWaterLevel() {
        waterLevel -= 1;
        if (waterLevel == thirstThreshold - 1) {
            System.out.println(this.toString() + " at (" + currentLocation.x() + ", " + currentLocation.y() + ") is getting thirsty! ");
        }
    }

    public void decrementHungerLevel() {
        hitPoints -= 1;
        if (hitPoints == hungerThreshold - 1) {
            System.out.println(this.toString() + " at (" + currentLocation.x() + ", " + currentLocation.y() + ") is getting hungry! ");
        }
    }

    public void quench(int quenchedAmount) {
        waterLevel += quenchedAmount;
        waterLevel = Math.min(waterLevel, maxWaterLevel);
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
        Action nextAction;
        currentLocation = map.locationOf(this);
        this.age += 1;

        //lay egg if pregnant
        if (layEggCounter > 0) {
            layEggCounter -= 1;
            if (layEggCounter == 0) {
                return new LayEggAction();
            }
        }

        // decrement hp and water levels based on consciousness
        if (!isConscious()) {
            unconsciousTurnsCounter += 1;
            if (!isUnconsciousDueToThirst()) {
                decrementWaterLevel();
            }
            else {
                unconsciousTurnsCounterThirst += 1;
            }
        }
        else if (isUnconsciousDueToThirst()) {
            unconsciousTurnsCounterThirst += 1;
            decrementHungerLevel();
        }
        else {
            decrementHungerLevel();
            decrementWaterLevel();
        }

        // check for death
        if (!isConscious() || isUnconsciousDueToThirst()) {
            if (unconsciousTurnsCounter == maxUnconsciousTurns
                    || unconsciousTurnsCounterThirst == maxUnconsciousTurnsDueToThirst) {
                return new DeathAction();
            }
            else {
                return new DoNothingAction();
            }
        }

        unconsciousTurnsCounter = 0;
        unconsciousTurnsCounterThirst = 0;

        //Stay in tree if pregnant Pterodactyl
        if ((this instanceof Pterodactyl) && layEggCounter > 0) {
            return new DoNothingAction();
        }

        //mate if adjacent to mate
        BreedBehaviour breedBehaviour = new BreedBehaviour();
        Action breedBehaviourAction = breedBehaviour.getAction(this, map);
        if (breedBehaviourAction instanceof BreedAction
                && hitPoints >= breedThreshold) {
            return breedBehaviourAction;
        }

        //sip/find water if thirsty
        if (waterLevel < thirstThreshold) {
            FindWaterBehaviour findWaterBehaviour = new FindWaterBehaviour();
            nextAction = findWaterBehaviour.getAction(this, map);
            if (nextAction != null) {
                return nextAction;
            }
        }

        //consume/move towards food if hungry
        if (hitPoints < hungerThreshold) {
            FindFoodBehaviour findFoodBehaviour = new FindFoodBehaviour();
            nextAction = findFoodBehaviour.getAction(this, map);
            if (nextAction != null) {
                return nextAction;
            }
        }

        //move towards mate
        if (breedBehaviourAction instanceof MoveActorAction) {
            return breedBehaviourAction;
        }

        //move towards player if not Pterodactyl
        if (!(this instanceof Pterodactyl)) {
            MoveToPlayerBehaviour moveToPlayerBehaviour = new MoveToPlayerBehaviour();
            nextAction = moveToPlayerBehaviour.getAction(this, map);
            if (nextAction != null) {
                return nextAction;
            }
            return (new WanderBehaviour()).getAction(this, map);
        }

        //move towards closest tree
        FindTreeBehaviour findTreeBehaviour = new FindTreeBehaviour();
        return findTreeBehaviour.getAction(this, map);
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

    public int quenchAmount() {
        if (this instanceof Brachiosaur) {
            return 80;
        }
        return 30;
    }

    public boolean isUnconsciousDueToThirst() {
        return waterLevel == 0;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }
}
