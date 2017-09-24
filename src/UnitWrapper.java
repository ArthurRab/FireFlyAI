import com.orbischallenge.firefly.client.objects.models.FriendlyUnit;
import com.orbischallenge.firefly.client.objects.models.Unit;
import com.orbischallenge.game.engine.Point;

import java.util.ArrayList;

public class UnitWrapper {
    private Mission mission;
    private FriendlyUnit unit;
    public ArrayList<Float> comparisonValues;

    public UnitWrapper() {
        comparisonValues = new ArrayList<>();
    }

    public UnitWrapper(FriendlyUnit unit) {
        this();
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
        return mission == null || m.getPriority() > mission.getPriority();
    }

    public void deleteMission() {
        Mission m = this.mission;
        this.mission = null;
        m.onDelete();
    }


    public Unit getUnit() {
        return unit;
    }

    public void setUnit(FriendlyUnit unit) {
        this.unit = unit;
    }

    public int getHealth() {
        return unit.getHealth();
    }

    public Point getPosition() {
        return unit.getPosition();
    }

    public void update(){
        if(mission!=null){
            PlayerAI.world.move(unit, mission.getMovePosition());
        }else{
            PlayerAI.world.move(unit, unit.getPosition());
        }

        if(mission!=null && mission.isCompleted()){
            deleteMission();
        }
    }
}
