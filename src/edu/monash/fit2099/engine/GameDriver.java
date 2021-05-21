package edu.monash.fit2099.engine;

import edu.monash.fit2099.engine.*;
import game.EcoPoints;
import game.Util;
import game.behaviour_action.QuitAction;

import java.util.*;


public class GameDriver {
    // 1 if challenge mode, 2 if sandbox mode, 3 if exiting
    private int gameMode;
    private int maxGameTurns;
    private int ecoPointsNumber;
    private boolean exitCheck = false;
    private int rainCounter = 0;
    protected Display display;
    protected ArrayList<GameMap> gameMaps = new ArrayList<GameMap>();
    protected ActorLocations actorLocations = new ActorLocations();
    protected Actor player; // We only draw the particular map this actor is on.
    protected Map<Actor, Action> lastActionMap = new HashMap<Actor, Action>();

    public GameDriver(Display display) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Rogue Dino!!");
        boolean validStart = false;
        while (!validStart) {
            System.out.println("Press 1 to play Challenge Mode");
            System.out.println("Press 2 to play Sandbox Mode");
            System.out.println("Press 3 to Exit");
            String selection = scanner.nextLine();
            switch (selection) {
                case "1":
                    System.out.println("Please enter the number of moves");
                    String moveNumberSelection = scanner.nextLine();
                    try {
                        maxGameTurns = Integer.parseInt(moveNumberSelection);
                    } catch (Exception e) {
                        System.out.println("Enter a valid number of moves");
                        break;
                    }

                    System.out.println("Please enter number of eco points");
                    String ecoPointsNumberSelection = scanner.nextLine();
                    try {
                        ecoPointsNumber = Integer.parseInt(moveNumberSelection);
                        validStart = true;
                    } catch (Exception e) {
                        System.out.println("Enter a valid number of eco points");
                        break;
                    }
                    System.out.println("Welcome to Challenge Mode");
                    gameMode = 1;
                    break;

                case "2":
                    System.out.println("Welcome to Sandbox Mode");
                    gameMode = 2;
                    validStart = true;
                    break;
                case "3":
                    gameMode = 3;
                    exitCheck = true;
                    validStart = true;
                    break;
                default:
                    System.out.println("Please enter a valid number");
            }
        }
        Objects.requireNonNull(display);
        this.display = display;

    }


    /**
     * Add a GameMap to the World.
     *
     * @param gameMap the GameMap to add
     */
    public void addGameMap(GameMap gameMap) {
        Objects.requireNonNull(gameMap);
        gameMaps.add(gameMap);
        gameMap.actorLocations = actorLocations;
    }

    /**
     * Set an actor as the player. The map is drawn just before this Actor's turn
     *
     * @param player   the player to add
     * @param location the Location where the player is to be added
     */
    public void addPlayer(Actor player, Location location) {
        this.player = player;
        actorLocations.add(player, location.map().at(location.x(), location.y()));
        actorLocations.setPlayer(player);
    }

    public boolean isRaining() {
        rainCounter++;
        if (rainCounter == 10) {
            rainCounter = 0;
            Random random = new Random();
            int rainChance = random.nextInt(100);
            System.out.println(rainChance < 20);
            return rainChance < 20;
        }
        return false;
    }

    /**
     * Run the game.
     * <p>
     * On each iteration the gameloop does the following: - displays the player's
     * map - processes the actions of every Actor in the game, regardless of map
     * <p>
     * We could either only process the actors on the current map, which would make
     * time stop on the other maps, or we could process all the actors. We chose to
     * process all the actors.
     *
     * @throws IllegalStateException if the player doesn't exist
     */
    public boolean run() {
        int currentTurn = -1;
        if (player == null)
            throw new IllegalStateException();

        // initialize the last action map to nothing actions;
        for (Actor actor : actorLocations) {
            lastActionMap.put(actor, new DoNothingAction());
        }

        // This loop is basically the whole game
        while (!exitCheck) {
            currentTurn ++;
            if (gameMode == 1){
                if (currentTurn > maxGameTurns){
                    return endGame();
                }
            }

            GameMap playersMap = actorLocations.locationOf(player).map();
            playersMap.draw(display);
            System.out.println("Current Turn: " + currentTurn);

            if (isRaining()) {
                Util.rainThisTick = true;
            }
            else {
                Util.rainThisTick = false;
            }

            // Tick over all the maps. For the map stuff.
            for (GameMap gameMap : gameMaps) {
                gameMap.tick();
            }

            // Process all the actors.
            for (Actor actor : actorLocations) {
                stillRunning();
                if (!exitCheck)
                    processActorTurn(actor);
            }
            stillRunning();
        }
        return endGame();
    }

    /**
     * Gives an Actor its turn.
     * <p>
     * The Actions an Actor can take include:
     * <ul>
     * <li>those conferred by items it is carrying</li>
     * <li>movement actions for the current location and terrain</li>
     * <li>actions that can be done to Actors in adjacent squares</li>
     * <li>actions that can be done using items in the current location</li>
     * <li>skipping a turn</li>
     * </ul>
     *
     * @param actor the Actor whose turn it is.
     */
    protected void processActorTurn(Actor actor) {
        Location here = actorLocations.locationOf(actor);
        GameMap map = here.map();

        Actions actions = new Actions();
        for (Item item : actor.getInventory()) {
            actions.add(item.getAllowableActions());
            // Game rule. If you're carrying it, you can drop it.
            actions.add(item.getDropAction());
        }

        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();

            // Game rule. You don't get to interact with the ground if someone is standing
            // on it.
            if (actorLocations.isAnActorAt(destination)) {
                actions.add(actorLocations.getActorAt(destination).getAllowableActions(actor, exit.getName(), map));
            } else {
                actions.add(destination.getGround().allowableActions(actor, destination, exit.getName()));
            }
            actions.add(destination.getMoveAction(actor, exit.getName(), exit.getHotKey()));
        }

        for (Item item : here.getItems()) {
            actions.add(item.getAllowableActions());
            // Game rule. If it's on the ground you can pick it up.
            actions.add(item.getPickUpAction());
        }
        actions.add(new DoNothingAction());

        Action action = actor.playTurn(actions, lastActionMap.get(actor), map, display);
        // if action is quit Action
        // return
        if (action instanceof QuitAction){
            exitCheck = true;
            return;
        }
        lastActionMap.put(actor, action);

        String result = action.execute(actor, map);
        display.println(result);
    }

    /**
     * Returns true if the game is still running.
     * <p>
     * The game is considered to still be running if the player is still around.
     *
     * @return true if the player is still on the map.
     */
    protected void stillRunning() {
        if (!actorLocations.contains(player)){
            exitCheck = true;
        }
    }

    /**
     * Return a string that can be displayed when the game ends.
     *
     * @return the string "Game Over"
     */
    protected String endGameMessage() {
        return "Game Over";
    }

    protected boolean endGame(){
        if (gameMode == 1){
            if (ecoPointsNumber > EcoPoints.getEcoPoints()){
                System.out.println("You lost Challenge Mode! You did not get enough eco points in the required amount of turns");
            }
            else{
                System.out.println("You win Challenge Mode! You got enough eco points");
            }
        }
        else if (gameMode == 2) {
            System.out.println("You have finished playing Sandbox mode!");
        }
        else if (gameMode == 3){
            while (true){
                System.out.println("You have chosen to exit");
                System.out.println("Press 1 to confirm");
                System.out.println("Press 2 to cancel");
                Scanner scanner1 = new Scanner(System.in);
                String selection1 = scanner1.nextLine();
                switch (selection1){
                    case "1":
                        return true;
                    case "2":
                        return false;
                    default:
                        System.out.println("Please enter valid input");
                }
            }


        }
        exitCheck = true;

        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Press 1 if you would like to play again");
            System.out.println("Press 2 if you would like to quit");
            String selection = scanner.nextLine();
            switch (selection) {
                case "1":
                    return false;
                case "2":
                    return true;
                default:
                    System.out.println("Please enter a valid input");
            }
        }

    }
}



