import com.orbischallenge.firefly.client.objects.models.FriendlyUnit;
import com.orbischallenge.game.engine.Point;

import java.util.ArrayList;

public class UnitWrapper {
    private Mission mission;
    private String unitID;
    public ArrayList<Float> comparisonValues;

    public UnitWrapper() {
        comparisonValues = new ArrayList<>();
    }

    public UnitWrapper(String ID) {
        this();
        setUnitID(ID);
    }

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
        mission.setWorker(this);
    }


    public boolean willTakeMission(Mission m) {
        return mission == null || m.getPriority() > mission.getPriority();
    }

    public void deleteMission() {
        Mission m = this.mission;
        this.mission = null;
        m.onDelete();
    }

    private FriendlyUnit getUnit() {
        return PlayerAI.world.getUnit(unitID);
    }


    public void setUnitID(String id) {
        unitID = id;
    }

    public int getHealth() {
        return getUnit().getHealth();
    }

    public Point getPosition() {
        return getUnit().getPosition();
    }

    public void update() {
        if (mission != null) {
            PlayerAI.world.move(getUnit(), mission.getMovePosition());
        } else {
            PlayerAI.world.move(getUnit(), getUnit().getPosition());
        }

        if (mission != null && mission.isCompleted()) {
            deleteMission();
        }
    }
}
