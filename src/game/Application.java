package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.World;
import game.dinosaurs.*;
import game.groundPackage.Dirt;
import game.groundPackage.Floor;
import game.groundPackage.Tree;
import game.groundPackage.Wall;

/**
 * The main class for the Jurassic World game.
 *
 */
public class Application {

	public static void main(String[] args) {
		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Tree());
		
		List<String> map = Arrays.asList(
		"_...............................................................................",
		"................................................................................",
		".....#######....................................................................",
		".....#_____#....................................................................",
		".....#_____#....................................................................",
		".....###.###....................................................................",
		"................................................................................",
		"......................................+++.......................................",
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
		"..............................................................................._");
		GameMap gameMap = new GameMap(groundFactory, map );
		world.addGameMap(gameMap);
		
		Actor player = new Player("Player", '@', 100);
		world.addPlayer(player, gameMap.at(9, 4));
		
		// Place a pair of stegosaurs in the middle of the map
		gameMap.at(30, 12).addActor(new Stegosaur("Stegosaur"));
		gameMap.at(32, 12).addActor(new Stegosaur("Stegosaur"));

//		System.out.println(gameMap.at(79,24).getGround().getDisplayChar());
//		System.out.println(gameMap.getXRange().max());
//		System.out.println(gameMap.getXRange().min());
//		System.out.println(gameMap.getYRange().max());
//		System.out.println(gameMap.at(79, 24).getGround().getDisplayChar());
		Util.locateItems(gameMap.at(0, 0), "Fruit");
		world.run();
	}
}
