import com.orbischallenge.firefly.client.objects.models.EnemyUnit;
import com.orbischallenge.firefly.client.objects.models.FriendlyUnit;
import com.orbischallenge.firefly.client.objects.models.World;
import com.orbischallenge.game.engine.Point;
import org.monte.media.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class PlayerAI {
    // Any field declarations go here

    public static World world;
    public static UnitWrapper[] friendlyUnits;
    public static Collection<Point> AVOID_AT_ALL_COSTS = new ArrayList<>();

    HashMap<String, UnitWrapper> unitIDToWrapper = new HashMap<String, UnitWrapper>();

    NestLocationFinder nlf = new NestLocationFinder();
    ArrayList<Point> nests;

    public PlayerAI() {
        // Any instantiation code goes here


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
        UnitWrapper[] fUnits = new UnitWrapper[friendlyUnits.length];
        for (int i=0; i<friendlyUnits.length;i++) {
            FriendlyUnit f = friendlyUnits[i];
            UnitWrapper w = unitIDToWrapper.getOrDefault(f.getUuid(), null);
            if (w == null) {
                w = new UnitWrapper(f);
                unitIDToWrapper.put(f.getUuid(), w);
            }

            fUnits[i] = w;
        }

        PlayerAI.friendlyUnits = fUnits;
        if (turns == 0) {
            nests = nlf.findNestLocations(PlayerAI.world, world.getFriendlyNestPositions()[0], 4);
            for (Point p : nlf.getNeighbours(world, nests)) {
                MissionManager.getInstance().addMission(new FillLocationMission(world.getTileAt(p), 1f));
            }
        }

        MissionManager.getInstance().distributeMissions();
        System.out.println(MissionManager.getInstance().pendingMissions.size());
        System.out.println(MissionManager.getInstance().saveForLater.size());

        System.out.println(PlayerAI.friendlyUnits.length);
        for (UnitWrapper i:PlayerAI.friendlyUnits) {
            i.update();
        }




        /* Fly away to freedom, daring fireflies
        Build thou nests
        Grow, become stronger
        Take over the world */
        /*
        for (FriendlyUnit unit: friendlyUnits) {
            List<Point> path = world.getShortestPath(unit.getPosition(),
                                                     world.getClosestCapturableTileFrom(unit.getPosition(), null).getPosition(),
                                                     null);
            if (path != null) world.move(unit, path.get(0));
        }*/
        turns += 1;


    }
}