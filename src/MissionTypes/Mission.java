package MissionTypes;
import General.UnitWrapper;


import com.orbischallenge.firefly.client.objects.models.Tile;

import java.util.Comparator;

public abstract class Mission implements Comparator<UnitWrapper> {
    public static final float LOW_PRIORTY = 0;
    public static final float MEDIUM_PRIORTY = 5;
    public static final float HIGH_PRIORTY = 10;


    float priority;
    Tile destination;

    public UnitWrapper x;


}
