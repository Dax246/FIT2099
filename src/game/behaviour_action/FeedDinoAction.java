package game.behaviour_action;

import edu.monash.fit2099.engine.*;
import game.dinosaurs.Allosaur;
import game.dinosaurs.Brachiosaur;
import game.dinosaurs.Dinosaur;
import game.dinosaurs.Stegosaur;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FeedDinoAction extends Action {

    @Override
    public String execute(Actor actor, GameMap map) {
        Location actorLocation = map.locationOf(actor);
        List<Exit> exits = actorLocation.getExits();
        for (Exit exit: exits){
            if (exit.getDestination().containsAnActor() && exit.getDestination().getActor() instanceof Dinosaur){
                Actor recipient = exit.getDestination().getActor();

                // Finds which foods the player holds in their inventory
                boolean fruitCheck = false;
                Item fruit = null;
                boolean vegetarianMealKitCheck = false;
                Item vegetarianMealKit = null;
                boolean carnivoreMealKitCheck = false;
                Item carnivoreMealKit = null;
                boolean eggCheck = false;
                Item egg = null;

                for (Item item: actor.getInventory()){
                    if (item.getDisplayChar() == 'f'){
                        fruitCheck = true;
                        fruit = item;
                    }
                    else if (item.getDisplayChar() == 'v'){
                        vegetarianMealKitCheck = true;
                        vegetarianMealKit = item;
                    }
                    else if (item.getDisplayChar() == 'c'){
                        carnivoreMealKitCheck = true;
                        carnivoreMealKit = item;
                    }
                    else if (item.getDisplayChar() == 'e'){
                        eggCheck = true;
                        egg = item;
                    }
                }

                if (recipient instanceof Stegosaur){
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("1: Feed Stegosaur Fruit");
                    System.out.println("2: Feed Stegosaur Vegetarian Meal Kit");
                    String selection = scanner.nextLine();
                    switch (selection){
                        case "1":
                            if (fruitCheck){
                                actor.removeItemFromInventory(fruit);
                                recipient.heal(20);
                                return "Player fed Fruit to Stegosaur";
                            }
                            return "Player does not have Fruit";
                        case "2":
                            if (vegetarianMealKitCheck){
                                actor.removeItemFromInventory(vegetarianMealKit);
                                // Ensures healed to full
                                recipient.heal(((Stegosaur) recipient).getMaxHitPoints());
                                return "Player fed Vegetarian Meal Kit to Stegosaur";
                            } return "Player does not have Fruit";
                        default:
                            return "Invalid Choice Selected";
                    }
                }

                else if (recipient instanceof Brachiosaur){
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("1: Feed Brachiosaur Fruit");
                    System.out.println("2: Feed Brachiosaur Vegetarian Meal Kit");
                    String selection = scanner.nextLine();
                    switch (selection){
                        case "1":
                            if (fruitCheck){
                                actor.removeItemFromInventory(fruit);
                                recipient.heal(20);
                                return "Player fed Fruit to Brachiosaur";
                            }
                            return "Player does not have Fruit";
                        case "2":
                            if (vegetarianMealKitCheck){
                                actor.removeItemFromInventory(vegetarianMealKit);
                                // Ensures healed to full
                                recipient.heal(((Brachiosaur) recipient).getMaxHitPoints());
                                return "Player fed Vegetarian Meal Kit to Brachiosaur";
                            } return "Player does not have a Vegetarian Meal Kit";
                        default:
                            return "Invalid Choice Selected";
                    }
                }

                else if (recipient instanceof Allosaur){
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("1: Feed Brachiosaur Egg");
                    System.out.println("2: Feed Brachiosaur Carnivore Meal Kit");
                    String selection = scanner.nextLine();
                    switch (selection){
                        case "1":
                            if (eggCheck){
                                actor.removeItemFromInventory(egg);
                                recipient.heal(10);
                                return "Player fed Egg to Allosaur";
                            }
                            return "Player does not have a Egg";
                        case "2":
                            if (carnivoreMealKitCheck){
                                actor.removeItemFromInventory(carnivoreMealKit);
                                // Ensures healed to full
                                recipient.heal(((Allosaur) recipient).getMaxHitPoints());
                                return "Player fed Carnivore Meal Kit to Allosaur";
                            } return "Player does not have a Carnivore Meal Kit";
                        default:
                            return "Invalid Choice Selected";
                    }
                }


            }
        }
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Player Feeds Dinosaur";
    }
}
