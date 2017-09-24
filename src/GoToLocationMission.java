
import com.orbischallenge.firefly.client.objects.models.Tile;
import com.orbischallenge.firefly.objects.enums.Direction;
import com.orbischallenge.game.engine.Point;

import java.util.List;

public class GoToLocationMission extends Mission {
    List<Point> path;
    List<Direction> directions;

    public GoToLocationMission(Tile d, float p) {
        super(d, p);
    }

    public void onStart() {
        path = PlayerAI.world.getShortestPath(getWorker().getPosition(), getDestination().getPosition(), PlayerAI.AVOID_AT_ALL_COSTS);
        path.add(0, getWorker().getPosition());
    }

    @Override
    public Point getMovePosition() {
        Point playerPos = getWorker().getPosition();
        int index = path.indexOf(playerPos);
        /*
        System.out.println();
        System.out.println(getWorker().getUnit().getUuid());
        System.out.println(getWorker().getPosition());
        System.out.println(path);
        System.out.println(index);
        path = path.subList(index, path.size());
        System.out.println(path);
        System.out.println(path.get(1));
        System.out.println();
        */
        if (path.size() == 1) {
            setCompleted(true);
            return playerPos;
        }

        return path.get(1);

    }

    @Override
    public int compare(UnitWrapper o1, UnitWrapper o2) {
        return 0;
    }
}
