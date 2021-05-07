package game.groundPackage;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.behaviour_action.PurchaseAction;

import java.util.HashMap;

public class VendingMachine extends Ground {
    private HashMap<String, Integer>  goodsPrice = new HashMap<String, Integer>();

    public HashMap<String, Integer> getGoodsPrice() {
        return goodsPrice;
    }

    private void setupVendingMachine(){
        goodsPrice = getGoodsPrice();
        goodsPrice.put("Fruit", 30);
        goodsPrice.put("Vegetarian meal kit", 100);
        goodsPrice.put("Carnivore meal kit", 500);
        goodsPrice.put("Stegosaur egg", 200);
        goodsPrice.put("Brachiosaur egg", 500);
        goodsPrice.put("Allosaur egg", 1000);
        goodsPrice.put("Laser gun", 50);
    }

    public VendingMachine(){
        super('M');
        setupVendingMachine();
    }

    public int getItemPrice(String item){
        goodsPrice = getGoodsPrice();
        return goodsPrice.get(item);
    }

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = new Actions();
        actions.add(new PurchaseAction());
        return actions;
    }
}
