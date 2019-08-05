package algorhithm;

import java.util.Comparator;

public class AStarComparator implements  Comparator<Schedule>{

    /**
     * TODO This function should sort by the current bottom cost total then by the level
     * @
     * @param t1
     * @return
     */

    @Override
    public int compare(Schedule schedule, Schedule t1) {
        int result = schedule.costToBottomLevel - t1.costToBottomLevel;
        if(result == 0){
            result = schedule.currentLevel - t1.currentLevel;
        }
        return result;
    }
}
