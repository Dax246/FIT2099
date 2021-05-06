package game.groundPackage;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;
import game.CarnivoreMealKit;
import game.Fruit;
import game.behaviour_action.PickFruitAction;

import java.util.Random;

public class Bush extends CarnivoreMealKit.Flora {

    public Bush() {
        super('b');
    }



    @Override
    public void tick(Location location){
        super.tick(location);

        Random random = new Random();
        int fruitChance = random.nextInt(100);
        if (fruitChance <= 10){
            boolean addedFruitValidly = addFruit(location);
            assert addedFruitValidly : "Bush can only add fruit to a bush";
        }

    }

    //TODO: Add to capabilities
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction){
        Actions actions = new Actions();
        if (getGrownFruits().size() > 0){
            actions.add(new PickFruitAction());
        }
        return actions;
    }

    @Override
    public int numberOfFruit() {
        return getGrownFruits().size();
    }

    private boolean addFruit(Location location){
        //TODO: Decide whether to put in a parent class as bush and tree both use this
        boolean isValid = false;
        if (location.getGround() instanceof Bush){
            ((Bush) location.getGround()).getGrownFruits().add(new Fruit());
            isValid = true;
        }
        return isValid;
    }
}
