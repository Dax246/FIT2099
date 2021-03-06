package game;

import edu.monash.fit2099.engine.*;
import game.dinosaurs.Egg;
import game.groundPackage.Lake;
import game.groundPackage.Tree;

import java.util.*;

/**
 * @author Allan Chan and Damien Ambegoda
 * @version 2.0.0
 * @see GameMap
 * Class representing Utility functions that are commonly used through the program.
 */
public class Util {
    /**
     * Method to locate items with name objectName
     * @param source order of locations to return from
     * @param objectName item name
     * @return arraylist of Locations with item
     */
    public static ArrayList<Location> locateObjects(Location source, String objectName) {
        NumberRange xRange = source.map().getXRange();
        NumberRange yRange = source.map().getYRange();
        GameMap gameMap = source.map();
        ArrayList<Location> locations = new ArrayList<Location>();
        ArrayList<ArrayList<Boolean>> visited = new ArrayList<ArrayList<Boolean>>();

        //xrange is width (col)
        //yrange is height (row)
        for (int i = 0; i < yRange.max() + 1; i++) {
            ArrayList<Boolean> rowArrayList = new ArrayList<Boolean>();
            for (int j = 0; j < xRange.max() + 1; j++) {
                rowArrayList.add(false);
            }
            visited.add(rowArrayList);
        }

        Queue<Location> bfs = new LinkedList<Location>();
        bfs.add(source);

        while (bfs.size() > 0) {
            Location currentLocation = bfs.remove();
            visited.get(currentLocation.y()).set(currentLocation.x(), true);

            //Do object check after popping from queue
            if (objectName == "Actor" && currentLocation.containsAnActor()
                    && !(currentLocation.getActor() instanceof Player)) {
                locations.add(currentLocation);
            }
            else if (objectName == "Water" && currentLocation.getGround() instanceof Lake
                    && ((Lake) currentLocation.getGround()).getCapacity() > 0
                    && (!gameMap.isAnActorAt(currentLocation) || currentLocation == source)) {
                locations.add(currentLocation);
            }
            else if (objectName == "Tree" && currentLocation.getGround() instanceof Tree
                    && (!gameMap.isAnActorAt(currentLocation) || currentLocation == source)) {
                locations.add(currentLocation);
            }
            else {
                if (!gameMap.isAnActorAt(currentLocation) || currentLocation == source) {
                    Item item = Util.retrieveItem(objectName, currentLocation.getItems());
                    if (item != null) {
                        locations.add(currentLocation);
                    }
                }
            }

            //add each unvisited neighbour
            for (Exit neighbour : currentLocation.getExits()) {
                Location currentNeighbour = neighbour.getDestination();
                if (walkable(currentNeighbour) && !visited.get(currentNeighbour.y()).get(currentNeighbour.x())) {
                    bfs.add(currentNeighbour);
                    visited.get(currentNeighbour.y()).set(currentNeighbour.x(), true);
                }
            }
        }
        return locations;
    }

    /**
     * Checks if location is reachable
     *
     * @param location target location
     * @return boolean if reachable or not
     */
    private static boolean walkable(Location location) {
        GameMap map = location.map();
        NumberRange xRange = map.getXRange();
        NumberRange yRange = map.getYRange();
        if (!map.isAnActorAt(location) && xRange.contains(location.x())
                && yRange.contains(location.y())) {
            return true;
        }
        return false;
    }

    /**
     * Returns item of type itemType
     * @param itemType name of item
     * @param locationList item list in location
     * @return item in location
     */
    public static Item retrieveItem(String itemType, List<Item> locationList) {
        for (Item item: locationList) {
            if (itemType == "Egg" && item instanceof Egg) {
                return item;
            }
            else if (item.toString() == itemType) {
                return item;
            }
        }
        return null;
    }

    /**
     * Connects the first and second map together using exits
     * @param southMap Map placed below the other map
     * @param northMap Map placed above the other map
     */
    public static void ConnectMapNorth(GameMap southMap, GameMap northMap){
        NumberRange width = southMap.getXRange();
        NumberRange height = southMap.getYRange();
        for (int x : width){
            Location southLocation = southMap.at(x, 0);
            Location northLocation = northMap.at(x, height.max());
            southLocation.addExit(new Exit("to North Map", northLocation, "8"));
            northLocation.addExit(new Exit("to South Map", southLocation, "2"));
        }
    }

    public static boolean rainThisTick = false;
}
