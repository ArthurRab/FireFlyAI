package MissionTypes;

import General.PlayerAI;
import General.UnitWrapper;

import java.util.ArrayList;
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
        pendingMissions = saveForLater;
        saveForLater = new PriorityQueue<Mission>(0, new MissionPriorityComparator());
    }

    public void update() {

    }

    public PriorityQueue<Mission> pendingMissions = new PriorityQueue<Mission>(0, new MissionPriorityComparator());
    public PriorityQueue<Mission> saveForLater = new PriorityQueue<Mission>(0, new MissionPriorityComparator());

    public void addMission(Mission m) {
        pendingMissions.add(m);
    }

    public void distributeMissions() {
        while (!pendingMissions.isEmpty()) {
            Mission m = pendingMissions.poll();
            ArrayList<UnitWrapper> applicants = new ArrayList<UnitWrapper>();
            for (UnitWrapper u : PlayerAI.friendlyUnits) {
                if (u.willTakeMission(m)) {
                    applicants.add(u);
                }
            }

            if (applicants.isEmpty()) {
                saveForLater.add(m);
            }

            UnitWrapper chosenOne = m.chooseUnit(applicants);

            chosenOne.setMission(m);
            m.onStart();
        }
    }

}
