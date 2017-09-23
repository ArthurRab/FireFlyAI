package General;

import com.orbischallenge.firefly.client.objects.models.EnemyUnit;
import com.orbischallenge.firefly.client.objects.models.FriendlyUnit;
import com.orbischallenge.firefly.client.objects.models.World;
import com.orbischallenge.firefly.client.objects.models.Tile;

import com.orbischallenge.game.engine.Point;
import com.orbischallenge.firefly.objects.enums.Direction;

import com.orbischallenge.logging.Log;

import java.util.*;

public class Difficulty implements Comparable<Difficulty>{
    private boolean possible;
    private int nearbyWalls, nearbyFriendly, wallsNearAdjacent;

    public Difficulty(Tile t, World world){
        if (!t.isNeutral()){
            possible = false;
        }
        else{
            for (Tile a: world.getTilesAround(t.getPosition()).values()){

            }
        }

    }

    @Override
    public int compareTo(Difficulty o) {
        return 0;
    }
}
