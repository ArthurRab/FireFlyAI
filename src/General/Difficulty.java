package General;

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
