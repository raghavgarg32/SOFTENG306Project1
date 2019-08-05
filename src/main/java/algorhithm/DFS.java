package algorhithm;

import graph.Graph;

import java.util.Stack;

public class DFS {
    private final int numP;
    private Stack<Schedule> scheduleStack;
    Graph graph;
    private int boundValue;

    public DFS(int numProcessors, Graph g) {
        graph = g;
        scheduleStack = new Stack<Schedule>();
        numP = numProcessors;

        //Init state
        scheduleStack.push(new Schedule(numP, graph));
        boundValue = Integer.MAX_VALUE;



    }

    public Schedule runDFS() {
        Schedule bestSchedule = new Schedule(numP, graph);
        while (!scheduleStack.empty()) {
            Schedule schedule = scheduleStack.pop();



            int currentBoundValue = boundValue;
            if (schedule.currentCost < currentBoundValue) {
                if (schedule.allVisited()) {
                    boundValue = schedule.currentCost;
                    bestSchedule = schedule;
                } else {
                    for (Schedule nextSchedule : schedule.generatePossibilities()) {
                        System.out.println(nextSchedule);

                        scheduleStack.push(nextSchedule);
                    }
                }
            }

        }
        return bestSchedule;
    }

}