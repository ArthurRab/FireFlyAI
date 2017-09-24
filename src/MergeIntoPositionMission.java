import com.orbischallenge.firefly.client.objects.models.Tile;
import com.orbischallenge.game.engine.Point;

import java.util.List;

public class MergeIntoPositionMission extends GoToLocationMission {
    int health;

    public MergeIntoPositionMission(int hp, Tile d, float p) {
        super(d, p);
        health = hp;
    }

    public void onStart() {

        health -= getWorker().getHealth();
        if (health > 0) {
            MissionManager.getInstance().addMission(new MergeIntoPositionMission(health - getWorker().getHealth(), getDestination(), getPriority() - 0.3f));
        }
    }


    @Override
    public void onDelete() {
        if (!isCompleted()) {
            MissionManager.getInstance().addMission(this);
        }
    }

    public int compare(UnitWrapper o1, UnitWrapper o2) {

        for (UnitWrapper o : new UnitWrapper[]{o1, o2}) {
            if (o.comparisonValues.isEmpty()) {
                List<Point> path = PlayerAI.world.getShortestPath(o.getPosition(), getDestination().getPosition(), PlayerAI.AVOID_AT_ALL_COSTS);
                if (path != null)
                    o.comparisonValues.add((float) -path.size());
                else
                    o.comparisonValues.add(-1000f);
            }

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
