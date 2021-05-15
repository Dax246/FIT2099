package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.*;
import game.dinosaurs.*;
import game.groundPackage.*;

/**
 * @author Allan Chan and Damien Ambegoda
 * @version 1.0.0
 * @see World
 * The main class for the Jurassic World game.
 */
public class Application {
	/**
	 * Main method that is executed to run game
	 * @param args any arguments given
	 */
	public static void main(String[] args) {

		boolean quitCheck = false;
		while (!quitCheck){
			GameStart gameStart = new GameStart(new Display());

			FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Tree(), new VendingMachine(), new Lake());

			List<String> map = Arrays.asList(
					"................................................................................",
					"................................................................................",
					".....#######....................................................................",
					".....#_____#....................................................................",
					".....#_____#....................................................................",
					".....###.###....................................................................",
					"................................................................................",
					".......v..............................+++.......................................",
					".......................................++++.....................................",
					"...................................+++++........................................",
					".....................................++++++.....................................",
					"......................................+++.......................................",
					".....................................+++........................................",
					"................................................................................",
					"............+++.................................................................",
					".............+++++............~~~...............................................",
					"...............++........................................+++++..................",
					".............+++....................................++++++++....................",
					"............+++.......................................+++.......................",
					"................................................................................",
					".........................................................................++.....",
					"........................................................................++.++...",
					".........................................................................++++...",
					"..........................................................................++....",
					"................................................................................");
			GameMap gameMap = new GameMap(groundFactory, map );
			gameStart.addGameMap(gameMap);
			GameMap gameMap2 = new GameMap(groundFactory, '.', gameMap.getXRange().max()+1, gameMap.getYRange().max()+1);
			gameStart.addGameMap(gameMap2);
			Util.ConnectMapNorth(gameMap, gameMap2);

			Actor player = new Player("Player", '@', 100);
			gameStart.addPlayer(player, gameMap.at(9, 4));

			gameMap.at(28, 12).addActor(new Pterodactyl("Pterodactyl", false));
			quitCheck = gameStart.run();
		}

		// Commented out code from before
//		World world = new World(new Display());

//		world.addGameMap(gameMap);
//		world.addGameMap(gameMap2);
//		world.addPlayer(player, gameMap.at(9, 4));
//		gameMap.at(30, 12).addActor(new Allosaur("Allosaur", false));
//		gameMap.at(28, 12).addActor(new Pterodactyl("Pterodactyl", false));

//		// Create a herd of 2 male and 2 female Brachiosaurs
//		gameMap.at(28, 12).addActor(new Brachiosaur("Brachiosaur", false));
//		gameMap.at(30, 12).addActor(new Brachiosaur("Brachiosaur", false));
//		gameMap.at(32, 12).addActor(new Brachiosaur("Brachiosaur", false));
//		gameMap.at(34, 12).addActor(new Brachiosaur("Brachiosaur", false));
//		((Brachiosaur) gameMap.getActorAt(gameMap.at(28, 12))).setSex('M');
//		((Brachiosaur) gameMap.getActorAt(gameMap.at(30, 12))).setSex('M');
//		((Brachiosaur) gameMap.getActorAt(gameMap.at(32, 12))).setSex('F');
//		((Brachiosaur) gameMap.getActorAt(gameMap.at(34, 12))).setSex('F');
//
//		//Place a triplet of Stegosaurs in the middle of the map
//		gameMap.at(20, 12).addActor(new Stegosaur("Stegosaur", false));
//		gameMap.at(22, 12).addActor(new Stegosaur("Stegosaur", false));
//		gameMap.at(24, 12).addActor(new Stegosaur("Stegosaur", false));

//		world.run();




	}
}
