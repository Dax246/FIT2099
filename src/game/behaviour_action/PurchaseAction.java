package game.behaviour_action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.*;
import game.dinosaurs.AlloEgg;
import game.dinosaurs.BrachEgg;
import game.dinosaurs.StegEgg;

import java.util.Scanner;

public class PurchaseAction extends Action {

    @Override
    public String execute(Actor actor, GameMap map) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1: Purchase Fruit for 30 eco points");
        System.out.println("2: Purchase Vegetarian Meal Kit for 100 eco points");
        System.out.println("3: Purchase Carnivore Meal Kit for 500 eco points");
        System.out.println("4: Purchase Stegosaur Egg for 200 eco points");
        System.out.println("5: Purchase Brachiosaur Egg for 500 eco points");
        System.out.println("6: Purchase Allosaur Egg for 1000 eco points");
        System.out.println("7: Purchase Laser Egg for 500 eco points");
        System.out.println("8: Display eco points");
        String selection = scanner.nextLine();
        switch (selection){
            case "1":
                if (EcoPoints.getEcoPoints() >= 30){
                    Fruit fruit = new Fruit('G');
                    actor.addItemToInventory(fruit);
                    EcoPoints.decreaseEcoPoints(30);
                    return "Purchased Fruit";
                }
                return "Insufficient eco points";
            case "2":
                if (EcoPoints.getEcoPoints() >= 100){
                    VegetarianMealKit vegetarianMealKit = new VegetarianMealKit();
                    actor.addItemToInventory(vegetarianMealKit);
                    EcoPoints.decreaseEcoPoints(100);
                    return "Purchased vegetarian meal kit";
                }
                return "Insufficient eco points";
            case "3":
                if (EcoPoints.getEcoPoints() >= 500){
                    CarnivoreMealKit carnivoreMealKit = new CarnivoreMealKit();
                    actor.addItemToInventory(carnivoreMealKit);
                    EcoPoints.decreaseEcoPoints(500);
                    return "Purchased carnivore meal kit";
                }
                return "Insufficient eco points";
            case "4":
                if (EcoPoints.getEcoPoints() >=200){
                    StegEgg stegEgg = new StegEgg();
                    actor.addItemToInventory(stegEgg);
                    EcoPoints.decreaseEcoPoints(200);
                    return "Purchased stegosaur egg";
                }
                return "Insufficient eco points";
            case "5":
                if (EcoPoints.getEcoPoints() >= 500){
                    BrachEgg brachEgg = new BrachEgg();
                    actor.addItemToInventory(brachEgg);
                    EcoPoints.decreaseEcoPoints(500);
                    return "Purchased brachiosaur egg";
                }
                return "Insufficient eco points";
            case "6":
                if (EcoPoints.getEcoPoints() >= 1000){
                    AlloEgg alloEgg = new AlloEgg();
                    actor.addItemToInventory(alloEgg);
                    EcoPoints.decreaseEcoPoints(1000);
                    return "Purchased allosaur egg";
                }
                return "Insufficient eco points";
            case "7":
                if (EcoPoints.getEcoPoints() >= 500){
                    LaserGun laserGun = new LaserGun();
                    actor.addItemToInventory(laserGun);
                    EcoPoints.decreaseEcoPoints(500);
                    return "Purchased laser egg";
                }
                return "Insufficient eco points";
            case "8":
                return Integer.toString(EcoPoints.getEcoPoints());
            default:
                return "Invalid Choice selected";
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Purchase from Vending Machine";
    }
}
