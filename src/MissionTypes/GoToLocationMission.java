package MissionTypes;

import General.PlayerAI;
import General.UnitWrapper;
import com.orbischallenge.firefly.objects.enums.Direction;
import com.orbischallenge.game.engine.Point;

import java.util.List;

public class GoToLocationMission extends Mission {
    List<Point> path;
    List<Direction> directions;

    public void onStart() {
        path = PlayerAI.world.getShortestPath(getWorker().getPosition(), getDestination().getPosition(), PlayerAI.AVOID_AT_ALL_COSTS);
        path.add(0, getWorker().getPosition());
    }

    @Override
    public void onLocationReached() {
        setCompleted(true);
    }

    @Override
    public Point getMoveDirection() {
        Point playerPos = getWorker().getPosition();
        int index = 0;
        for (int i = 0; i < path.size(); i++) {
            if (playerPos.equals(path.get(i))) {
                index = i;
                break;
            }
        }


        path = path.subList(index, path.size());

        if (path.size() == 1) {
            return playerPos;
        }

        return path.get(1);

    }

    @Override
    public int compare(UnitWrapper o1, UnitWrapper o2) {
        return 0;
    }
}
