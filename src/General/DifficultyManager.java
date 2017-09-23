package General;

import com.orbischallenge.firefly.client.objects.models.World;
import com.orbischallenge.firefly.client.objects.models.Tile;

import java.util.*;

public class DifficultyManager {
    private HashMap<Tile, Difficulty> tileScores = new HashMap<Tile, Difficulty>();
    private World world;

    public void update(World world){
        this.world = world;
        tileScores.clear();

    }

    public Difficulty getDifficulty(Tile t){
        if (!tileScores.containsKey(t)) tileScores.put(t, new Difficulty(t, world));
        return tileScores.get(t);
    }
}

