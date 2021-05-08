package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.World;
import game.dinosaurs.*;
import game.groundPackage.*;

/**
 * The main class for the Jurassic World game.
 *
 */
public class Application {

	public static void main(String[] args) {
		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Tree(), new VendingMachine());
		
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
		".............+++++..............................................................",
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
		world.addGameMap(gameMap);
		
		Actor player = new Player("Player", '@', 100);
		world.addPlayer(player, gameMap.at(9, 4));
		
		// Create a herd of 2 male and 2 female Brachiosaurs
		gameMap.at(28, 12).addActor(new Brachiosaur("Brachiosaur", false));
		gameMap.at(30, 12).addActor(new Brachiosaur("Brachiosaur", false));
		gameMap.at(32, 12).addActor(new Brachiosaur("Brachiosaur", false));
		gameMap.at(34, 12).addActor(new Brachiosaur("Brachiosaur", false));
		((Brachiosaur) gameMap.getActorAt(gameMap.at(28, 12))).setSex('M');
		((Brachiosaur) gameMap.getActorAt(gameMap.at(30, 12))).setSex('M');
		((Brachiosaur) gameMap.getActorAt(gameMap.at(32, 12))).setSex('F');
		((Brachiosaur) gameMap.getActorAt(gameMap.at(34, 12))).setSex('F');

		//Place a triplet of Stegosaurs in the middle of the map
		gameMap.at(20, 12).addActor(new Stegosaur("Stegosaur", false));
		gameMap.at(22, 12).addActor(new Stegosaur("Stegosaur", false));
		gameMap.at(24, 12).addActor(new Stegosaur("Stegosaur", false));

		world.run();
	}
}
