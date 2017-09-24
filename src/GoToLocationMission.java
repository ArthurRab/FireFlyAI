
import com.orbischallenge.firefly.client.objects.models.Tile;
import com.orbischallenge.game.engine.Point;

import java.util.List;

public class GoToLocationMission extends Mission {
    List<Point> path;

    public GoToLocationMission(Tile d, float p) {
        super(d, p);
    }

    public void onStart() {
        path = PlayerAI.world.getShortestPath(getWorker().getPosition(), getDestination().getPosition(), PlayerAI.AVOID_AT_ALL_COSTS);
        if(path!=null)
            path.add(0, getWorker().getPosition());
        else
         setCompleted(true);
    }

    @Override
    public Point getMovePosition() {
        if(!isCompleted()) {
            Point playerPos = getWorker().getPosition();
            int index;
            if (path.contains(playerPos)) {
                index = path.indexOf(playerPos);
            } else {
                PlayerAI.world.getShortestPath(getWorker().getPosition(), getDestination().getPosition(), PlayerAI.AVOID_AT_ALL_COSTS);
                path.add(0, getWorker().getPosition());
                index = 0;
            }
        /*
        System.out.println();
        System.out.println(getWorker().getUnit().getUuid());
        System.out.println(getWorker().getPosition());
        System.out.println(path);
        System.out.println(index);

        System.out.println(path);
        System.out.println(path.get(1));
        System.out.println();
        */
            path = path.subList(index, path.size());
            if (path.size() == 1) {
                setCompleted(true);
                return playerPos;
            }

            return path.get(1);
        }
        return getWorker().getPosition();

    }

    public List<Point> getPath(){
        return path;
    }

    @Override
    public int compare(UnitWrapper o1, UnitWrapper o2) {
        return 0;
    }
}
