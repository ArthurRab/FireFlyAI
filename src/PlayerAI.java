import com.orbischallenge.firefly.client.objects.models.EnemyUnit;
import com.orbischallenge.firefly.client.objects.models.FriendlyUnit;
import com.orbischallenge.firefly.client.objects.models.World;
import com.orbischallenge.game.engine.Point;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
<<<<<<< HEAD
import java.util.*;
=======
>>>>>>> parent of 15408c8... changes

public class PlayerAI {
    // Any field declarations go here

    public static World world;
    public static ArrayList<UnitWrapper> friendlyUnits;
    public static Collection<Point> AVOID_AT_ALL_COSTS = new ArrayList<>();

    HashMap<String, UnitWrapper> unitIDToWrapper = new HashMap<String, UnitWrapper>();

    NestLocationFinder nlf = new NestLocationFinder();
    List<Point> nests;

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


        for (String i : unitIDToWrapper.keySet()) {
            if (!PlayerAI.friendlyUnits.contains(unitIDToWrapper.get(i))) {
                unitIDToWrapper.get(i).deleteMission();
                unitIDToWrapper.remove(i);
            }
        }
<<<<<<< HEAD
<<<<<<< HEAD

        //if (MissionManager.getInstance().pendingMissions.isEmpty() && MissionManager.getInstance().saveForLater.isEmpty()){

        if (turns <= 0) {
            Point start = world.getFriendlyNestPositions()[0];
            AVOID_AT_ALL_COSTS.add(start);
            nests = nlf.findNestLocations(PlayerAI.world, start, 20);
            Collections.sort(nests, (o1, o2) -> {
                Point diff1 = o1.subtract(start).getMod(world.getWidth(), world.getHeight()),
                        diff2 = o2.subtract(start).getMod(world.getWidth(), world.getHeight());
                return Math.max(Math.abs(diff1.getX()), Math.abs(diff1.getY()))
                        - Math.max(Math.abs(diff2.getX()), Math.abs(diff2.getY()));
            });
            nests = nests.subList(0, 8);

=======
        if (MissionManager.getInstance().pendingMissions.isEmpty() && MissionManager.getInstance().saveForLater.isEmpty()){
        //if (turns == 0){
            AVOID_AT_ALL_COSTS.add(world.getFriendlyNestPositions()[0]);
            nests = nlf.findNestLocations(PlayerAI.world, world.getFriendlyNestPositions()[0], 4);
>>>>>>> parent of 8faffbb... debugging
            AVOID_AT_ALL_COSTS.addAll(nests);
=======
        if (turns == 0) {
            nests = nlf.findNestLocations(PlayerAI.world, world.getFriendlyNestPositions()[0], 4);
>>>>>>> parent of 15408c8... changes
            for (Point p : nlf.getNeighbours(world, nests)) {
                MissionManager.getInstance().addMission(new FillLocationMission(world.getTileAt(p), 1f));
                break;
            }
<<<<<<< HEAD
            //System.out.println(MissionManager.getInstance().pendingMissions.size());
            System.out.println("NESTS:");
            //for (Point m: nests) System.out.println(m);
            for (Mission m: MissionManager.getInstance().pendingMissions) System.out.println(m.getDestination());
            System.out.println();
=======
            System.out.println(MissionManager.getInstance().pendingMissions.size());
            System.out.println(MissionManager.getInstance().pendingMissions.peek().getDestination());
>>>>>>> parent of 15408c8... changes
        }

        MissionManager.getInstance().distributeMissions();
<<<<<<< HEAD
        for (Mission m : MissionManager.getInstance().saveForLater) {
            System.out.println(MissionManager.getInstance().pendingMissions.size());
            System.out.println(MissionManager.getInstance().saveForLater.size());
            //System.out.println("==DESTS==");
            //for (Mission m: MissionManager.getInstance().saveForLater){
            //   System.out.println(m.getDestination());
            //}
            //System.out.println();
=======
        System.out.println(MissionManager.getInstance().pendingMissions.size());
        System.out.println(MissionManager.getInstance().saveForLater.size());
>>>>>>> parent of 15408c8... changes

            System.out.println(PlayerAI.friendlyUnits.size());


        for (FriendlyUnit u : friendlyUnits) {
            if (u.getLastMoveResult() == MoveResult.MOVE_SUCCESS) {
                System.out.println("OVER HERE:");
                System.out.println(u.getUuid());
                System.out.println(u.getPosition());
                System.out.println();
            }
        }

<<<<<<< HEAD
            for (UnitWrapper i : PlayerAI.friendlyUnits) {
            /*if (i.getMission() == null){
                System.out.println("INACTIVE");
                System.out.println(i.getPosition());
                System.out.println();
            }
            else{
                System.out.println("ACTIVE");
                System.out.println(((GoToLocationMission) i.getMission()).getPath());
                System.out.println();
            }*/
                i.update();
            }
=======
        for (UnitWrapper i : PlayerAI.friendlyUnits) {
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
>>>>>>> parent of 15408c8... changes

            turns += 1;

        }
    }
}