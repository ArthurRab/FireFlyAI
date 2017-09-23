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
            possible = true;
            for (Tile a: world.getTilesAround(t.getPosition()).values()){
                if (world.isWall(a.getPosition())) nearbyWalls += 1;
                else{
                    if (a.isFriendly()) nearbyFriendly += 1;
                    for (Direction d : Direction.getOrderedDirections()){
                        if (world.isWall(a.getPosition().add(d.getDirectionDelta()))){
                            wallsNearAdjacent += 1;
                        }
                    }
                }
            }
        }

    }

    public float score(){
        return 2*nearbyFriendly + 1.5f*nearbyWalls - 1*wallsNearAdjacent;
    }

    @Override
    public int compareTo(Difficulty o) {
        if (!this.possible && !o.possible) return 0;
        if (!this.possible) return -1;
        if (!o.possible) return 1;

        return Float.compare(this.score(), o.score());
    }
}
