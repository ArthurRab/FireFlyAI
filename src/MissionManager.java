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

    }

    public void clearAllMissions() {
        for (UnitWrapper u : PlayerAI.friendlyUnits) {
            u.deleteMission();
        }
        pendingMissions.clear();
        saveForLater.clear();
    }

    public void update() {
        pendingMissions = saveForLater;
        saveForLater = new PriorityQueue<Mission>(1, new MissionPriorityComparator());
    }

    public PriorityQueue<Mission> pendingMissions = new PriorityQueue<>(1, new MissionPriorityComparator());
    public PriorityQueue<Mission> saveForLater = new PriorityQueue<>(1, new MissionPriorityComparator());

    public void addMission(Mission m) {
        pendingMissions.add(m);
    }

    public void distributeMissions() {
        while (!pendingMissions.isEmpty()) {
            Mission m = pendingMissions.poll();
            ArrayList<UnitWrapper> applicants = new ArrayList<>();
            for (UnitWrapper u : PlayerAI.friendlyUnits) {
                if (u.willTakeMission(m)) {
                    applicants.add(u);
                }
            }

            if (applicants.isEmpty()) {
                saveForLater.add(m);
                saveForLater.addAll(pendingMissions);
                pendingMissions.clear();
            } else {

                UnitWrapper chosenOne = m.chooseUnit(applicants);

                chosenOne.setMission(m);
                m.onStart();
            }
        }
    }

}
