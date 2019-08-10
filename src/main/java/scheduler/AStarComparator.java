package scheduler;

import java.util.Comparator;

/**
 * Class to help implement the priority queue required by the A star comparator.
 * Cost to bottom level is checked before the current level
 */
public class AStarComparator implements  Comparator<State>{


    @Override
    public int compare(State state, State t1) {
        int result = state.costToBottomLevel - t1.costToBottomLevel;
        if(result == 0){
            result = state.currentLevel - t1.currentLevel;
        }
        return result;
    }
}
