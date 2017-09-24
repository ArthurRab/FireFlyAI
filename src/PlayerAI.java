import com.orbischallenge.firefly.client.objects.models.EnemyUnit;
import com.orbischallenge.firefly.client.objects.models.FriendlyUnit;
import com.orbischallenge.firefly.client.objects.models.World;
import com.orbischallenge.firefly.objects.enums.MoveResult;
import com.orbischallenge.game.engine.Point;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class PlayerAI {
    // Any field declarations go here

    public static World world;
    public static ArrayList<UnitWrapper> friendlyUnits = new ArrayList<>();
    public static Collection<Point> AVOID_AT_ALL_COSTS = new ArrayList<>();

    HashMap<String, UnitWrapper> unitIDToWrapper = new HashMap<String, UnitWrapper>();

    NestLocationFinder nlf = new NestLocationFinder();
    ArrayList<Point> nests;

    FriendlyUnit pilot = null;

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


        PlayerAI.friendlyUnits.clear();
        for (int i = 0; i < friendlyUnits.length; i++) {
            FriendlyUnit f = friendlyUnits[i];
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
        if (MissionManager.getInstance().pendingMissions.isEmpty() && MissionManager.getInstance().saveForLater.isEmpty()){
        //if (turns == 0){
            AVOID_AT_ALL_COSTS.add(world.getFriendlyNestPositions()[0]);
            nests = nlf.findNestLocations(PlayerAI.world, world.getFriendlyNestPositions()[0], 4);
            AVOID_AT_ALL_COSTS.addAll(nests);
            for (Point p : nlf.getNeighbours(world, nests)) {
                MissionManager.getInstance().addMission(new FillLocationMission(world.getTileAt(p), 1f));

            }
            System.out.println(MissionManager.getInstance().pendingMissions.size());
            //for (Mission m: MissionManager.getInstance().pendingMissions) System.out.println(m.getDestination());
            System.out.println();
        }


        MissionManager.getInstance().distributeMissions();
        System.out.println(MissionManager.getInstance().pendingMissions.size());
        System.out.println(MissionManager.getInstance().saveForLater.size());
        System.out.println("==DESTS==");
        for (Mission m: MissionManager.getInstance().saveForLater){
            System.out.println(m.getDestination());
        }
        System.out.println();

        System.out.println(PlayerAI.friendlyUnits.size());

/*
        for (FriendlyUnit u : friendlyUnits) {
            if (u.getLastMoveResult() == MoveResult.MOVE_SUCCESS) {
                System.out.println("OVER HERE:");
                System.out.println(u.getUuid());
                System.out.println(u.getPosition());
                System.out.println();
            }
        }
*/

        for (UnitWrapper i : PlayerAI.friendlyUnits) {
            if (i.getMission() == null){
                System.out.println("INACTIVE");
                System.out.println(i.getPosition());
                System.out.println();
            }
            else{
                System.out.println("ACTIVE");
                System.out.println(((GoToLocationMission) i.getMission()).getPath());
                System.out.println();
            }
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