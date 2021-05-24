package game.behaviour_action;

import edu.monash.fit2099.engine.*;
import game.*;
import game.dinosaurs.AlloEgg;
import game.dinosaurs.BrachEgg;
import game.dinosaurs.PteroEgg;
import game.dinosaurs.StegEgg;
import game.groundPackage.VendingMachine;

import java.util.Scanner;

/**
 * @author Allan Chan and Damien Ambegoda
 * @version 2.0.0
 * @see game.Player
 * A class that figures out a MoveAction that will move the actor one step
 * closer to the player.
 */
public class PurchaseAction extends Action {
    /**
     * Purchases item from vending machine for player.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String that will be printed.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        VendingMachine vm = null;
        for (Exit exit : map.locationOf(actor).getExits()) {
            if (exit.getDestination().getGround() instanceof VendingMachine) {
                vm = (VendingMachine) exit.getDestination().getGround();
                break;
            }
        }
        if (vm == null) {
            throw new AssertionError("No vending machine nearby!");
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Ecopoints: " + Integer.toString(EcoPoints.getEcoPoints()));
        System.out.println("1: Purchase Fruit for 30 eco points");
        System.out.println("2: Purchase Vegetarian Meal Kit for 100 eco points");
        System.out.println("3: Purchase Carnivore Meal Kit for 500 eco points");
        System.out.println("4: Purchase Stegosaur Egg for 200 eco points");
        System.out.println("5: Purchase Brachiosaur Egg for 500 eco points");
        System.out.println("6: Purchase Allosaur Egg for 1000 eco points");
        System.out.println("7: Purchase Laser Gun for 500 eco points");
        System.out.println("8: Purchase Pterodactyl Egg for 200 eco points");
        String selection = scanner.nextLine();
        switch (selection){
            case "1":
                if (EcoPoints.getEcoPoints() >= vm.getItemPrice("Fruit")){
                    Fruit fruit = new Fruit('H');
                    actor.addItemToInventory(fruit);
                    EcoPoints.decreaseEcoPoints(vm.getItemPrice("Fruit"));
                    return "Purchased Fruit";
                }
                return "Insufficient eco points";
            case "2":
                if (EcoPoints.getEcoPoints() >= vm.getItemPrice("Vegetarian meal kit")){
                    VegetarianMealKit vegetarianMealKit = new VegetarianMealKit();
                    actor.addItemToInventory(vegetarianMealKit);
                    EcoPoints.decreaseEcoPoints(vm.getItemPrice("Vegetarian meal kit"));
                    return "Purchased vegetarian meal kit";
                }
                return "Insufficient eco points";
            case "3":
                if (EcoPoints.getEcoPoints() >= vm.getItemPrice("Carnivore meal kit")){
                    CarnivoreMealKit carnivoreMealKit = new CarnivoreMealKit();
                    actor.addItemToInventory(carnivoreMealKit);
                    EcoPoints.decreaseEcoPoints(vm.getItemPrice("Carnivore meal kit"));
                    return "Purchased carnivore meal kit";
                }
                return "Insufficient eco points";
            case "4":
                if (EcoPoints.getEcoPoints() >=vm.getItemPrice("Stegosaur egg")){
                    StegEgg stegEgg = new StegEgg();
                    actor.addItemToInventory(stegEgg);
                    EcoPoints.decreaseEcoPoints(vm.getItemPrice("Stegosaur egg"));
                    return "Purchased Stegosaur egg";
                }
                return "Insufficient eco points";
            case "5":
                if (EcoPoints.getEcoPoints() >= vm.getItemPrice("Brachiosaur egg")){
                    BrachEgg brachEgg = new BrachEgg();
                    actor.addItemToInventory(brachEgg);
                    EcoPoints.decreaseEcoPoints(vm.getItemPrice("Brachiosaur egg"));
                    return "Purchased Brachiosaur egg";
                }
                return "Insufficient eco points";
            case "6":
                if (EcoPoints.getEcoPoints() >= vm.getItemPrice("Allosaur egg")){
                    AlloEgg alloEgg = new AlloEgg();
                    actor.addItemToInventory(alloEgg);
                    EcoPoints.decreaseEcoPoints(vm.getItemPrice("Allosaur egg"));
                    return "Purchased Allosaur egg";
                }
                return "Insufficient eco points";
            case "7":
                if (EcoPoints.getEcoPoints() >= vm.getItemPrice("Laser gun")){
                    LaserGun laserGun = new LaserGun();
                    actor.addItemToInventory(laserGun);
                    EcoPoints.decreaseEcoPoints(vm.getItemPrice("Laser gun"));
                    return "Purchased laser gun";
                }
                return "Insufficient eco points";
            case "8":
                if (EcoPoints.getEcoPoints() >=vm.getItemPrice("Pterodactyl egg")){
                    PteroEgg pteroEgg = new PteroEgg();
                    actor.addItemToInventory(pteroEgg);
                    EcoPoints.decreaseEcoPoints(vm.getItemPrice("Pterodactyl egg"));
                    return "Purchased Pterodactyl egg";
                }
                return "Insufficient eco points";
            default:
                return "Invalid Choice selected";
        }
    }

    /**
     * Returns a descriptive string
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Purchase from Vending Machine";
    }
}
