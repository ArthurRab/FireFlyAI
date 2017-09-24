package MissionTypes;

import General.UnitWrapper;
import com.orbischallenge.firefly.objects.enums.Direction;

public class GoToLocationMission extends Mission {
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
