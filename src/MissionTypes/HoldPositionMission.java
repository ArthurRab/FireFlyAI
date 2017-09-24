package MissionTypes;

import General.UnitWrapper;
import com.orbischallenge.firefly.client.objects.models.Tile;
import com.orbischallenge.game.engine.Point;

public class HoldPositionMission extends GoToLocationMission {

    public HoldPositionMission(Tile d, float p) {
        super(d, p);
    }

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
