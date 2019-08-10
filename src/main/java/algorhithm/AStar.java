package algorhithm;

import graph.Graph;
import javafx.event.EventType;
import scheduler.AStarComparator;
import scheduler.State;
import visualisation.processor.listeners.ObservableAlgorithm;
import visualisation.processor.listeners.SchedulerListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Algorithm which deals with using the A star implementation. Here, a priority queue
 * is used to ensure that nodes with least cost are placed with greatest priority followed
 * by their level.
 */
public class AStar implements  Algorithm,ObservableAlgorithm {
    private List<SchedulerListener> listeners = new ArrayList<>();
    private int minFullPath = Integer.MAX_VALUE;
    private boolean traversed;
    private PriorityQueue<State> candidate;
    private HashSet<State> visited;
    private Graph graph;

    private State finalState;
    private int numberOfProcessors;

    public AStar(int numProcessors, Graph graph) {
        this.numberOfProcessors = numProcessors;
        candidate = new PriorityQueue<>(new AStarComparator());
        visited = new HashSet();
        this.graph = graph;
        traversed = false;
        candidate.add(new State(numberOfProcessors, graph));
    }

    /**
     * Runs the algorithm
     * @return
     */
    public State runAlgorithm() {
        finalState = null;
        while (!candidate.isEmpty() && candidate.peek().getCostToBottomLevel() <= minFullPath) {
            State s = candidate.poll();
            for (State s1 : s.generatePossibilities()) {
                if (!visited.contains(s1)) {
                    if (s1.getCostToBottomLevel() < minFullPath) {
                        candidate.add(s1);
                        if (s1.allVisited() && s1.getCostToBottomLevel() < minFullPath) {
                            minFullPath = s1.getCostToBottomLevel();
                            finalState = s1;
                        }
                    }
                    visited.add(s1);
                }
            }
        }
        fireEvent(AlgorithmEvents.GET_NUMBER_OF_PROCESSORS);
        fireEvent(AlgorithmEvents.ALGORITHM_FINISHED);
        return finalState;
    }

    @Override
    public void addListener(SchedulerListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(SchedulerListener listener) {
        listeners.remove(listener);
    }

    @Override
    public void fireEvent(AlgorithmEvents event) {
        switch (event) {
            case GET_NUMBER_OF_PROCESSORS:
                for (SchedulerListener listener : listeners) {
                    listener.getNumberOfProcessors(numberOfProcessors);
                }
                return;
            case ALGORITHM_FINISHED:
                for (SchedulerListener listener : listeners) {
                    listener.getState(finalState);
                }
                return;
        }
    }

    //Todo implement this class.
    /*
    Initialise MinFullPath to integer.Maxint

    Add the initial State(Empty, VisitedList(root),CandidateList(roots' children),currentCost) to the
    Priority Queue

    While the priorityQueue is not empty:
        Generate the possibilities involving all nodes in the candidate list
        If we have traversed all nodes and cost is less than the minFullPathCost:
            Add the possibilities onto the priority queue
        Else:
            Add the possibilities onto the priority queue
        Pop off the priority queue

        if current is full state and cheaper than minFullPath:
            replace minFullPath

            For all states in priority queue:
                If cost is less than the minFullPathCost:
                    Remove it from the priority queue
    done

    Select the State with cheapest DFS cost
     */
}
