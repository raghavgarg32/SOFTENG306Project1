package algorithm;

import scheduler.State;

import java.util.HashSet;
import java.util.PriorityQueue;

public class AStarThread extends Thread{

    private PriorityQueue<State> candidate;
    private HashSet<State> visited;
    HashSet<State> generatedStates;
    private int minFullPath;
    private State currentState;
    private State result;
    private int num;

    public AStarThread(int num){
        this.num = num;
    }
    public AStarThread(AStar astar){

    }
    @Override
    public void run() {
        /*if (!visited.contains(currentState)) {
            if (currentState.getCostToBottomLevel() < minFullPath) {
                candidate.add(currentState);
                if (currentState.allVisited() && currentState.getCostToBottomLevel() < minFullPath) {
                    minFullPath = currentState.getCostToBottomLevel();
                    result = currentState;
                }
            }
            visited.add(currentState);
        }*/
    }
    /*@Override
    public void run(){
        System.out.println(Integer.toString(num) + " Threads");
    }*/

}
