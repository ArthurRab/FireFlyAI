import com.orbischallenge.firefly.client.objects.models.Tile;
import com.orbischallenge.game.engine.Point;

public class HoldPositionMission extends GoToLocationMission {

    public HoldPositionMission(Tile d, float p) {
        super(d, p);
    }

    @Override
    public Point getMovePosition() {
        return super.getMovePosition();
    }

    @Override
    public int compare(UnitWrapper o1, UnitWrapper o2) {
        return 0;
    }
}
