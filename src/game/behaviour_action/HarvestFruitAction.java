package game.behaviour_action;

import edu.monash.fit2099.engine.*;
import game.EcoPoints;
import game.groundPackage.Flora;

import java.util.Random;

public class HarvestFruitAction extends Action {


    @Override
    public String execute(Actor actor, GameMap map){
        Location location = map.locationOf(actor);
        Random random = new Random();
        int HarvestChance = random.nextInt(100);
        if (location.getGround() instanceof Flora){
            if (HarvestChance <= 60){
                EcoPoints.increaseEcoPoints(10);
                actor.addItemToInventory(((Flora)location.getGround()).harvestFruit(location));
                return "Player has harvested a fruit at: (" + location.x() + ", " + location.y() + ")";
            } return "Player has searched for fruit but is unable to find any ripe ones at: (" + location.x() + ", " + location.y() + ")";
        }
        return "No Fruit to Harvest at: (" + location.x() + ", " + location.y() + ")";
    }

    @Override
    public String menuDescription(Actor actor){
        return "Player harvests Fruit";
    }





}
