package scheduler;

import java.util.Comparator;

public class AStarComparator implements  Comparator<State>{

    /**
     * TODO This function should sort by the current bottom cost total then by the level
     * @
     * @param t1
     * @return
     */

    @Override
    public int compare(State state, State t1) {
        int result = state.costToBottomLevel - t1.costToBottomLevel;
        if(result == 0){
            result = state.currentLevel - t1.currentLevel;
        }
        return result;
    }
}
