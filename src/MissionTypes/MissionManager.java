package MissionTypes;

import java.util.Collection;
import java.util.PriorityQueue;

public class MissionManager {

    private static MissionManager mm = null;

    public static MissionManager getInstance() {
        if (mm == null) {
            mm = new MissionManager();
        }
        return mm;
    }

    private MissionManager() {

    }

    public Collection<Mission> pendingMissions = new PriorityQueue<Mission>(0, new MissionPriorityComparator());

    public void addMission(Mission m) {
        pendingMissions.add(m);
    }

}
