package MissionTypes;

import General.UnitWrapper;
import com.orbischallenge.firefly.client.objects.models.Tile;
import com.orbischallenge.firefly.objects.enums.Direction;

import java.util.Comparator;

public abstract class Mission implements Comparator<UnitWrapper> {
    public static final float LOW_PRIORTY = 0;
    public static final float MEDIUM_PRIORTY = 5;
    public static final float HIGH_PRIORTY = 10;


    float priority;
    Tile destination;

    public abstract void onDelete();

    public abstract void onLocationReached();

    public abstract Direction getMoveDirection();

    public float getPriority() {
        return priority;
    }

}
