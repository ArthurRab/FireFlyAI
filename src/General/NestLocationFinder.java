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

    public ArrayList<Point> findNestLocations(World world, Point start){

        ArrayList nests = new ArrayList<Point>();
        ArrayList visited = new ArrayList<Point>();
        while (nests.size() < 4){
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
        }

        return nests;
    }

}
