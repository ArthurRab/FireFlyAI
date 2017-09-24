package MissionTypes;

import General.UnitWrapper;
import com.orbischallenge.game.engine.Point;

public class HoldPositionMission extends GoToLocationMission {

    @Override
    public void onLocationReached() {

    }

    @Override
    public Point getMoveDirection() {
        return super.getMoveDirection();
    }

    @Override
    public int compare(UnitWrapper o1, UnitWrapper o2) {
        return 0;
    }
}
