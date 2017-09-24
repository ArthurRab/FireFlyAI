
import com.orbischallenge.firefly.client.objects.models.Tile;
import com.orbischallenge.game.engine.Point;

import java.util.List;

public class GoToLocationMission extends Mission {
    List<Point> path;

    public GoToLocationMission(Tile d, float p) {
        super(d, p);
    }

    public void onStart() {
        path = PlayerAI.world.getShortestPath(getWorker().getPosition(), getDestination().getPosition(), PlayerAI.AVOID_AT_ALL_COSTS);
        path.add(0, getWorker().getPosition());
    }

    @Override
    public Point getMovePosition() {
        if (!isCompleted()) {
            Point playerPos = getWorker().getPosition();
            int index;
            if (path.contains(playerPos)) {
                index = path.indexOf(playerPos);
            } else {
                path = PlayerAI.world.getShortestPath(getWorker().getPosition(), getDestination().getPosition(), PlayerAI.AVOID_AT_ALL_COSTS);
                path.add(0, getWorker().getPosition());
                index = 0;
            }

            path = path.subList(index, path.size());
            if (path.size() == 1) {
                setCompleted(true);
                return playerPos;
            }
            return path.get(1);
        }
        return getWorker().getPosition();
    }

    @Override
    public int compare(UnitWrapper o1, UnitWrapper o2) {
        return 0;
    }
}
