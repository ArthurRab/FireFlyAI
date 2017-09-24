
import com.orbischallenge.firefly.client.objects.models.Tile;
import com.orbischallenge.game.engine.Point;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class Mission implements Comparator<UnitWrapper> {
    public static final float LOW_PRIORTY = 0;
    public static final float MEDIUM_PRIORTY = 5;
    public static final float HIGH_PRIORTY = 10;

    private boolean completed = false;


    private float priority;
    private Tile destination;

    public Mission(Tile d, float p) {
        destination = d;
        priority = p;
    }

    private UnitWrapper worker;

    public void onDelete() {
        if (!completed)
            MissionManager.getInstance().addMission(this);
    }

    public void onStart() {

    }


    public abstract Point getMovePosition();


    public UnitWrapper chooseUnit(List<UnitWrapper> u) {
        if (u.isEmpty()) {
            return null;
        }


        UnitWrapper best = Collections.max(u, this);
        Collections.sort(u, this);

        for (UnitWrapper unit : u) {
            unit.comparisonValues.clear();
        }


        return best;
    }

    public Tile getDestination() {
        return destination;
    }

    public void setDestination(Tile destination) {
        this.destination = destination;
    }

    public float getPriority() {
        return priority;
    }

    public void setPriority(float priority) {
        this.priority = priority;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public UnitWrapper getWorker() {
        return worker;
    }

    public void setWorker(UnitWrapper worker) {
        this.worker = worker;
    }


}
