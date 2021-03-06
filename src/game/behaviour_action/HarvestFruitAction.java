package game.behaviour_action;

import edu.monash.fit2099.engine.*;
import game.EcoPoints;
import game.Fruit;
import game.groundPackage.Flora;

import java.util.Random;

/**
 * @author Allan Chan and Damien Ambegoda
 * @version 2.0.0
 * @see game.Player
 * Behaviour that determines which eat action or movement to do.
 */
public class HarvestFruitAction extends Action {
    Location target;

    public HarvestFruitAction(Location target) {
        this.target = target;
    }

    /**
     * Removes fruit from bush/tree and adds to player inventory.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String to be printed on menu.
     */
    @Override
    public String execute(Actor actor, GameMap map){
        Random random = new Random();
        int HarvestChance = random.nextInt(100);
        if (target.getGround() instanceof Flora){
            if (HarvestChance <= 60){
                EcoPoints.increaseEcoPoints(EcoPoints.getGainEcoPoints().get("Fruit harvested"));
                boolean fruitToPick = ((Flora)target.getGround()).harvestFruit(target, 'B');
                if (!fruitToPick) {
                    fruitToPick = ((Flora)target.getGround()).harvestFruit(target, 'T');
                }
                if (fruitToPick) {
                    actor.addItemToInventory(new Fruit('H'));
                    return "Player has harvested a fruit at: (" + target.x() + ", " + target.y() + ")";
                }
            } return "Player has searched for fruit but is unable to find any ripe ones at: (" + target.x() + ", " + target.y() + ")";
        }
        return "No Fruit to Harvest at: (" + target.x() + ", " + target.y() + ")";
    }

    /**
     * Returns a descriptive string
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor){
        return "Player harvests Fruit";
    }





}
