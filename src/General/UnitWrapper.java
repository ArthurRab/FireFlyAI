package General;

import MissionTypes.Mission;
import com.orbischallenge.firefly.client.objects.models.Unit;

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
    }

    public boolean willTakeMission(Mission m) {
        return m.getPriority() >= mission.getPriority();
    }


    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
