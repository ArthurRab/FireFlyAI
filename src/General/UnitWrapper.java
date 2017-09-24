package General;

import MissionTypes.Mission;
import com.orbischallenge.firefly.client.objects.models.Unit;
import com.orbischallenge.game.engine.Point;

public class UnitWrapper {
    private Mission mission;
    private Unit unit;

    public UnitWrapper() {

    }

    public UnitWrapper(Unit unit) {
        setUnit(unit);
    }

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
        mission.setWorker(this);
    }

    public boolean willTakeMission(Mission m) {
        return m == null || m.getPriority() >= mission.getPriority();
    }

    public void deleteMission() {
        Mission m = this.mission;
        this.mission = null;
        m.onDelete();
    }


    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public int getHealth() {
        return unit.getHealth();
    }

    public Point getPosition() {
        return getPosition();
    }
}
