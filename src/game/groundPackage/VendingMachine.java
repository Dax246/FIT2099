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
    private HashMap<String, Integer>  goodsPrice = new HashMap<String, Integer>();

    /**
     * Getter of goodsPrice
     * @return goodsPrice
     */
    public HashMap<String, Integer> getGoodsPrice() {
        return goodsPrice;
    }

    /**
     * Initialises the price of each item
     */
    private void setupVendingMachine(){
        goodsPrice = getGoodsPrice();
        goodsPrice.put("Fruit", 30);
        goodsPrice.put("Vegetarian meal kit", 100);
        goodsPrice.put("Carnivore meal kit", 500);
        goodsPrice.put("Stegosaur egg", 200);
        goodsPrice.put("Brachiosaur egg", 500);
        goodsPrice.put("Allosaur egg", 1000);
        goodsPrice.put("Laser gun", 500);
    }

    /**
     * Constructor
     */
    public VendingMachine() {
        super('v');
        setupVendingMachine();
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
