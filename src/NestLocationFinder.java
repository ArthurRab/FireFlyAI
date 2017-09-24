import com.orbischallenge.firefly.client.objects.models.Tile;
import com.orbischallenge.firefly.client.objects.models.World;

import com.orbischallenge.game.engine.Point;
import com.orbischallenge.firefly.objects.enums.Direction;

import java.util.*;

public class NestLocationFinder {
    HashSet<Point> allDirections;

    public NestLocationFinder(){
        allDirections = new HashSet<>();
        Point zero = new Point(0,0);
        for (Direction d: Direction.getOrderedDirections()){
            for (Direction e: Direction.getOrderedDirections()){
                if (d != e && d.getDirectionDelta().add(e.getDirectionDelta()) != zero){
                    allDirections.add(d.getDirectionDelta().add(e.getDirectionDelta()));
                }
            }
            allDirections.add(d.getDirectionDelta());
        }
        System.out.println("ALL DIRECTIONS");
        System.out.println(allDirections);
        System.out.println();
    }

    public List<Point> findNestLocations(World world, Point start, int numNests){

        ArrayList nests = new ArrayList<Point>();
        HashSet<Point> visited = new HashSet<>();


        for (Point p: PlayerAI.AVOID_AT_ALL_COSTS) {
            nests.add(p);
            for (Point d : allDirections) {
                Point a = p.add(d).getMod(world.getWidth(), world.getHeight());
                visited.add(a);
            }
        }

        int initLen = nests.size();
        int counter = 0;

        while (nests.size() < initLen + numNests && counter < 20){
            System.out.println();
            System.out.println(start);
            System.out.print("VISTIED: ");
            System.out.println(visited.size());
            System.out.print("COUNTER: ");
            System.out.println(counter);
            System.out.println();
            Tile t = world.getClosestNeutralTileFrom(start, visited);

            if (t == null){

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
                    if (l == null) fail = true;
                    pathSum += l.size();
                }
            }
            if (fail || pathSum > 30) nests.remove(newPoint);
            else {
                for (Point p : allDirections) {
                    Point a = newPoint.add(p).getMod(world.getWidth(), world.getHeight());
                    visited.add(a);
                }
                counter += 1;
            }
        }

        return nests.subList(initLen, nests.size());
    }


    public HashSet<Point> getNeighbours(World world, List<Point> nests){
        HashSet<Point> neighbours = new HashSet();
        for (Point n : nests) {
            for (Direction d : Direction.getOrderedDirections()) {
                Point a = n.add(d.getDirectionDelta()).getMod(world.getWidth(), world.getHeight());
                if (!world.isWall(a) && !world.getTileAt(a).isFriendly()) neighbours.add(a);
            }
        }
        return neighbours;
    }


    public List<List<Point>> createPaths(World world, Point start, List<Point> nests){

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
