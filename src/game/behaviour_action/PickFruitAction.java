package game.behaviour_action;

import edu.monash.fit2099.engine.*;
import game.EcoPoints;
import game.groundPackage.Flora;

import java.util.List;

public class PickFruitAction extends Action {


    @Override
    public String execute(Actor actor, GameMap map){
        Location location = map.locationOf(actor);
        List<Exit> exits = location.getExits();
        for (Exit exit : exits ){
            Ground groundAtExit = exit.getDestination().getGround();
            // Fruit at exit
            if (groundAtExit instanceof Flora){
                if (((Flora) groundAtExit).numberOfFruit() >0){
                    actor.addItemToInventory(((Flora) groundAtExit).getFruit());
                    EcoPoints.increaseEcoPoints(10);
                }
            }
        }
        return "Player has harvested a fruit!";
    }

    @Override
    public String menuDescription(Actor actor){
        return "Player harvests Fruit";
    }





}
