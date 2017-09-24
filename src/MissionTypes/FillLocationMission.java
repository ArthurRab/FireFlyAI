package MissionTypes;

import General.PlayerAI;
import General.UnitWrapper;
import com.orbischallenge.game.engine.Point;

public class FillLocationMission extends GoToLocationMission {

    @Override
    public Point getMoveDirection() {
        if (getDestination().isFriendly()) {
            setCompleted(true);
            //FIX INEFICIENY HERE LATER - HE WAITS A TURN BEFORE MOVING - ADD A WAY TO REQUEST A MISSION
            return getWorker().getPosition();
        }

        return super.getMoveDirection();

    }

    public int compare(UnitWrapper o1, UnitWrapper o2) {
        if (o1.comparisonValues.isEmpty()) {
            o1.comparisonValues.add
                    ((float) PlayerAI.world.getShortestPath(o1.getPosition(), getDestination().getPosition(), PlayerAI.AVOID_AT_ALL_COSTS).size());
            o1.comparisonValues.add
                    ((float) -o1.getHealth());
        }
        if (o2.comparisonValues.isEmpty()) {
            o2.comparisonValues.add
                    ((float) PlayerAI.world.getShortestPath(o2.getPosition(), getDestination().getPosition(), PlayerAI.AVOID_AT_ALL_COSTS).size());
            o2.comparisonValues.add
                    ((float) -o2.getHealth());
        }

        for (int i = 0; i < o1.comparisonValues.size(); i++) {
            float x = o1.comparisonValues.get(i) - o2.comparisonValues.get(i);
            if (x > 0) {
                return 1;
            } else if (x < 0) {
                return -1;
            }
        }

        return 0;
    }
}
