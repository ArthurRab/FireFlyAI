package General;
import com.orbischallenge.firefly.client.objects.models.EnemyUnit;
import com.orbischallenge.firefly.client.objects.models.FriendlyUnit;
import com.orbischallenge.firefly.client.objects.models.World;
import com.orbischallenge.firefly.client.objects.models.Tile;

import com.orbischallenge.game.engine.Point;
import com.orbischallenge.firefly.objects.enums.Direction;

import com.orbischallenge.logging.Log;

import java.util.*;

public class NestLocationFinder {

    public ArrayList<Point> findNestLocations(World world, Point start, int numNests){

        ArrayList nests = new ArrayList<Point>();
        ArrayList visited = new ArrayList<Point>();
        int counter = 0;
        while (nests.size() < numNests && counter < 10){
            Point newPoint = world.getClosestNeutralTileFrom(start, visited).getPosition();
            visited.add(newPoint);
            nests.add(newPoint);
            int pathSum = 0;
            boolean fail = false;
            for (Direction d: Direction.getOrderedDirections()){
                Point a = newPoint.add(d.getDirectionDelta());
                if (!world.isWall(a) && !world.getTileAt(a).isFriendly()){
                    List l = world.getShortestPath(start, a, nests);
                    if (l == null) fail = true;
                    pathSum += l.size();
                }
            }
            if (fail || pathSum > 15) nests.remove(newPoint);
            else {
                for (Direction d : Direction.getOrderedDirections()) {
                    Point a = newPoint.add(d.getDirectionDelta());
                    visited.add(a);
                }
                counter += 1;
            }
        }

        return nests;
    }

    public List<List<Point>> createPaths(World world, Point start, ArrayList<Point> nests){
        HashSet<Point> neighbourhoods = new HashSet();
        ArrayList<List<Point>> paths = new ArrayList();
        HashSet<List<Point>> removedPaths = new HashSet();
        for (Point n: nests){
            for (Direction d: Direction.getOrderedDirections()) {
                Point a = n.add(d.getDirectionDelta());
                neighbourhoods.add(a);
            }
        }
        for (Point n: neighbourhoods){
            paths.add(world.getShortestPath(start, n, nests));
        }
        for (int i = 0; i < paths.size(); i++){
            for (int j = i+1; j < paths.size(); i++){
                List<Point> pathI = paths.get(i), pathJ = paths.get(j);
                if (pathI.containsAll(pathJ)){
                    removedPaths.add(pathJ);
                }
                else if(pathJ.containsAll(pathI)){
                    removedPaths.add(pathI);
                }
            }
        }
        paths.removeAll(removedPaths);
        paths.sort((o1, o2) -> o2.size() - o1.size());
        return paths;
    }

}
