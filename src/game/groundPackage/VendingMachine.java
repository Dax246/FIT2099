package game.groundPackage;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.behaviour_action.PurchaseAction;
import java.util.HashMap;

/**
 * @author Allan Chan and Damien Ambegoda
 * @version 1.0.0
 * @see Ground
 * A class that players interact with to puchase items
 */
public class VendingMachine extends Ground {
    /**
     * Hashmap to store prices
     */
    private HashMap<String, Integer> goodsPrice = new HashMap<String, Integer>(){{
        put("Fruit", 30);
        put("Vegetarian Meal Kit", 100);
        put("Carnivore Meal Kit", 500);
        put("Stegosaur Egg", 200);
        put("Peterodactyl Egg", 200);
        put("Brachiosaur Egg", 500);
        put("Allosaur Egg", 1000);
        put("Laser Gun", 500);

    }};
    /**
     * Getter of goodsPrice
     * @return goodsPrice
     */
    public HashMap<String, Integer> getGoodsPrice() {
        return goodsPrice;
    }


    /**
     * Constructor
     */
    public VendingMachine() {
        super('v');
    }

    /**
     * Returns if actor can walk through it
     * @param actor the Actor to check
     * @return false regarding if actor can walk through it
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    /**
     * Getter of item price
     * @param item price to get
     * @return int price of item
     */
    public int getItemPrice(String item){
        goodsPrice = getGoodsPrice();
        return goodsPrice.get(item);
    }

    /**
     * Actions player can interact with
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return Actions player can perform on it
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = new Actions();
        actions.add(new PurchaseAction());
        return actions;
    }
}
