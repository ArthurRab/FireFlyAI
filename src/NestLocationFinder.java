import com.orbischallenge.firefly.client.objects.models.Tile;
import com.orbischallenge.firefly.client.objects.models.World;

import com.orbischallenge.game.engine.Point;
import com.orbischallenge.firefly.objects.enums.Direction;

import java.util.*;

public class NestLocationFinder {

    public ArrayList<Point> findNestLocations(World world, Point start, int numNests) {

        ArrayList nests = new ArrayList<Point>();
        HashSet<Point> visited = new HashSet<>();
        if (PlayerAI.AVOID_AT_ALL_COSTS != null) {
            nests.addAll(PlayerAI.AVOID_AT_ALL_COSTS);
            visited.addAll(PlayerAI.AVOID_AT_ALL_COSTS);
        }
        int initLen = nests.size();
        int counter = 0;
        while (nests.size() < initLen + numNests && counter < 10) {

            Tile t = world.getClosestNeutralTileFrom(start, visited);
            if (t == null) {
                return nests;
            }
            Point newPoint = t.getPosition();
            visited.add(newPoint);
            nests.add(newPoint);
            int pathSum = 0;
            boolean fail = false;
            for (Direction d : Direction.getOrderedDirections()) {
                Point a = newPoint.add(d.getDirectionDelta()).getMod(world.getWidth(), world.getHeight());
                if (!world.isWall(a) && !world.getTileAt(a).isFriendly()) {
                    List l = world.getShortestPath(start, a, nests);
                    if (l == null) {
                        fail = true;
                        break;
                    }
                    pathSum += l.size();
                }
            }
            if (fail || pathSum > 15) nests.remove(newPoint);
            else {
                for (Direction d : Direction.getOrderedDirections()) {
                    Point a = newPoint.add(d.getDirectionDelta()).getMod(world.getWidth(), world.getHeight());
                    visited.add(a);
                }
                counter += 1;
            }
        }

        return nests;
    }

    public HashSet<Point> getNeighbours(World world, ArrayList<Point> nests) {
        HashSet<Point> neighbours = new HashSet();
        for (Point n : nests) {
            for (Direction d : Direction.getOrderedDirections()) {
                Point a = n.add(d.getDirectionDelta()).getMod(world.getWidth(), world.getHeight());
                if (!world.isWall(a) && !world.getTileAt(a).isFriendly()) neighbours.add(a);
            }
        }
        return neighbours;
    }

    public List<List<Point>> createPaths(World world, Point start, ArrayList<Point> nests) {
        HashSet<Point> neighbours = getNeighbours(world, nests);
        ArrayList<List<Point>> paths = new ArrayList();
        HashSet<List<Point>> removedPaths = new HashSet();

        for (Point n : neighbours) {
            paths.add(world.getShortestPath(start, n, nests));
        }
        for (int i = 0; i < paths.size(); i++) {
            for (int j = i + 1; j < paths.size(); i++) {
                List<Point> pathI = paths.get(i), pathJ = paths.get(j);
                if (pathI.containsAll(pathJ)) {
                    removedPaths.add(pathJ);
                } else if (pathJ.containsAll(pathI)) {
                    removedPaths.add(pathI);
                }
            }
        }
        paths.removeAll(removedPaths);
        paths.sort((o1, o2) -> o2.size() - o1.size());
        return paths;
    }

}
