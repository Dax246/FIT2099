package game;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.NumberRange;

import java.util.*;

public class Util {
    public static ArrayList<Location> locateItems(Location source, String itemName) {
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

        //visited[row][col] = source
        visited.get(source.y()).set(source.x(), true);

        //Do item checks here

        Queue<Location> bfs = new LinkedList<Location>();
        bfs.add(source);

        while (bfs.size() > 0) {
            Location currentLocation = bfs.remove();
            visited.get(currentLocation.y()).set(currentLocation.x(), true);

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

        return locations;
    }
}
