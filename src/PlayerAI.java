import com.orbischallenge.firefly.client.objects.models.EnemyUnit;
import com.orbischallenge.firefly.client.objects.models.FriendlyUnit;
import com.orbischallenge.firefly.client.objects.models.World;
import com.orbischallenge.game.engine.Point;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class PlayerAI {
    // Any field declarations go here

    public static World world;
    public static ArrayList<UnitWrapper> friendlyUnits = new ArrayList<>();
    public static Collection<Point> AVOID_AT_ALL_COSTS = new ArrayList<>();

    HashMap<String, UnitWrapper> unitIDToWrapper = new HashMap<String, UnitWrapper>();

    NestLocationFinder nlf = new NestLocationFinder();
    ArrayList<Point> nests;

    FriendlyUnit pilot = null;

    public MissionCreator mc;

    public PlayerAI() {

    }

    int turns = 0;


    /**
     * This method will get called every turn.
     *
     * @param world         The latest state of the world.
     * @param friendlyUnits An array containing all remaining firefly units in your team
     * @param enemyUnits    An array containing all remaining enemy firefly units
     */

    public void doMove(World world, FriendlyUnit[] friendlyUnits, EnemyUnit[] enemyUnits) {
        PlayerAI.world = world;
        MissionManager.getInstance().update();


        PlayerAI.friendlyUnits.clear();
        for (FriendlyUnit f : friendlyUnits) {
            UnitWrapper w = unitIDToWrapper.getOrDefault(f.getUuid(), null);
            if (w == null) {
                w = new UnitWrapper(f.getUuid());
                unitIDToWrapper.put(f.getUuid(), w);
            }
            PlayerAI.friendlyUnits.add(w);
        }

        Object[] temp = unitIDToWrapper.keySet().toArray();

        for (Object i : temp) {

            if (!PlayerAI.friendlyUnits.contains(unitIDToWrapper.get(i))) {
                unitIDToWrapper.get(i).deleteMission();
                unitIDToWrapper.remove(i);
            }
        }
        //if (MissionManager.getInstance().pendingMissions.isEmpty() && MissionManager.getInstance().saveForLater.isEmpty()){
        if (turns <= 0) {
            AVOID_AT_ALL_COSTS.add(world.getFriendlyNestPositions()[0]);
            nests = nlf.findNestLocations(PlayerAI.world, world.getFriendlyNestPositions()[0], 8);
            AVOID_AT_ALL_COSTS.addAll(nests);
            for (Point p : nlf.getNeighbours(world, nests)) {
                MissionManager.getInstance().addMission(new FillLocationMission(world.getTileAt(p), 1f));

            }
        }


        MissionManager.getInstance().distributeMissions();
        for (Mission m : MissionManager.getInstance().saveForLater) {
        }

        turns += 1;

    }
}