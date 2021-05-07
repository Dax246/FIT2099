package game;

import edu.monash.fit2099.engine.*;
import game.dinosaurs.Egg;
import game.groundPackage.Flora;

import java.util.*;

public class Util {

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

            //Do item checks after popping from queue
            if (objectName == "actor" && currentLocation.containsAnActor()) {
                locations.add(currentLocation);
            }
            else {
                boolean foundFruitInTreeOrBush = false;
                if (objectName == "fruit"
                        && (Character.toString(currentLocation.getGround().getDisplayChar()) == "b" ||
                        Character.toString(currentLocation.getGround().getDisplayChar()) == "t")
                        && ((Flora) currentLocation.getGround()).numberOfFruit() > 0) {
                    foundFruitInTreeOrBush = true;
                    locations.add(currentLocation);
                }
                if (!foundFruitInTreeOrBush) {
                    for (Item item: currentLocation.getItems()) {
                        if (objectName == "fruit" && item instanceof Fruit) {
                            locations.add(currentLocation);
                            break;
                        }
                        else if (objectName == "egg" && item instanceof Egg) {
                            locations.add(currentLocation);
                            break;
                        }
                    }
                }
            }

            //add each unvisited neighbour
            if (xRange.contains(currentLocation.x() + 1) && yRange.contains(currentLocation.y())) {
                if (!visited.get(currentLocation.y()).get(currentLocation.x() + 1)) {
                    bfs.add(gameMap.at(currentLocation.x() + 1, currentLocation.y()));
                    visited.get(currentLocation.y()).set(currentLocation.x() + 1, true);
                }
            }

            if (xRange.contains(currentLocation.x() - 1) && yRange.contains(currentLocation.y())) {
                if (!visited.get(currentLocation.y()).get(currentLocation.x() - 1)) {
                    bfs.add(gameMap.at(currentLocation.x() - 1, currentLocation.y()));
                    visited.get(currentLocation.y()).set(currentLocation.x() + 1, true);
                }
            }

            if (xRange.contains(currentLocation.x()) && yRange.contains(currentLocation.y() + 1)) {
                if (!visited.get(currentLocation.y() + 1).get(currentLocation.x())) {
                    bfs.add(gameMap.at(currentLocation.x(), currentLocation.y() + 1));
                    visited.get(currentLocation.y() + 1).set(currentLocation.x(), true);
                }
            }

            if (xRange.contains(currentLocation.x()) && yRange.contains(currentLocation.y() - 1)) {
                if (!visited.get(currentLocation.y() - 1).get(currentLocation.x())) {
                    bfs.add(gameMap.at(currentLocation.x(), currentLocation.y() - 1));
                    visited.get(currentLocation.y() - 1).set(currentLocation.x(), true);
                }
            }
        }
        for (Location location:locations) {
            System.out.println(location.x() + ", " + location.y());
        }
        return locations;
    }
}
