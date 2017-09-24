package MissionTypes;

import General.UnitWrapper;
import com.orbischallenge.firefly.objects.enums.Direction;
import com.orbischallenge.game.engine.Point;

import java.util.List;

public class FollowPathMission extends Mission {

    private List<Point> path;

    public FollowPathMission(List<Point> path) {
        this.path = path;
    }

    @Override
    public void onLocationReached() {
        setCompleted(true);
    }

    @Override
    public Direction getMoveDirection() {
        return null;
    }

    @Override
    public int compare(UnitWrapper o1, UnitWrapper o2) {
        return 0;
    }
}
