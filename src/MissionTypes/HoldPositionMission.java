package MissionTypes;

import General.UnitWrapper;
import com.orbischallenge.firefly.objects.enums.Direction;

public class HoldPositionMission extends GoToLocationMission {

    @Override
    public void onLocationReached() {

    }

    @Override
    public Direction getMoveDirection() {
        return super.getMoveDirection();
    }

    @Override
    public int compare(UnitWrapper o1, UnitWrapper o2) {
        return 0;
    }
}
