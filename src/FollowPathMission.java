
import com.orbischallenge.firefly.client.objects.models.Tile;
import com.orbischallenge.game.engine.Point;

import java.util.List;

public class FollowPathMission extends Mission {

    private List<Point> path;

    public FollowPathMission(List<Point> path, Tile t, float p) {
        super(t,p);
        this.path = path;
    }

    @Override
    public Point getMovePosition() {
        return null;
    }

    @Override
    public int compare(UnitWrapper o1, UnitWrapper o2) {
        return 0;
    }
}
