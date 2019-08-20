package algorithm;

import scheduler.State;

import java.util.HashSet;
import java.util.PriorityQueue;

public class AStarThread extends Thread{

    AStar astar;
    scheduler.State s;
    scheduler.State result;
    public AStarThread(AStar astar, scheduler.State s){
        this.astar = astar;
        this.s = s;
    }
    @Override
    public void run() {
        result = astar.lookAtState(s);
    }

    public scheduler.State getResult() {
        return result;
    }

}
