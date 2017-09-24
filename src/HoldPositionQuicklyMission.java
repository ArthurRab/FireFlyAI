import com.orbischallenge.firefly.client.objects.models.Tile;
import com.orbischallenge.game.engine.Point;

public class HoldPositionQuicklyMission extends MergeIntoPositionMission {

    public HoldPositionQuicklyMission(int hp, Tile d, float p) {
        super(hp, d, p);
    }

    @Override
    public Point getMovePosition() {
        Point p = super.getMovePosition();
        setCompleted(false);
        return p;
    }
}


