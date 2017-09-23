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

    @Override
    public int compareTo(Difficulty o) {
        if (o == WORST_DIFFICULTY){
            return 1;
        }
        return 0;
    }
}
