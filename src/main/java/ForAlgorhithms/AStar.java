package ForAlgorhithms;

import Graph.Graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class AStar {
    int minFullPath = Integer.MAX_VALUE;
    boolean traversed;
    PriorityQueue<State> candidate;
    HashSet<String> visited;
    //PriorityQueue<State> states = new PriorityQueue<>();

    Graph graph;

    public AStar(int numProcessors, Graph graph) {
        candidate = new PriorityQueue<>(new AStarComparator());
        visited = new HashSet();
        this.graph = graph;
        traversed = false;

        //Todo implement state with root vertex;
        candidate.add(new State(numProcessors, graph));
    }

    public State runAlgorhithm() {
        State result = null;
        while (!candidate.isEmpty() && candidate.peek().currentCost <= minFullPath) {
            State s = candidate.poll();
            for (State s1 : s.generatePossibilities()) {
                //TODO ensure toString creates a unique sorted schedule string
                if (!visited.contains(s1)) {
                    System.out.println(s1);
                    if (s1.currentCost < minFullPath) {
                        candidate.add(s1);
                        if (s1.allVisited() && s1.currentCost < minFullPath) {
                            minFullPath = s1.currentCost;
                        }
                    }
                    visited.add(s1.toString());

                }
            }
//TODO figure out a way to remove from teh priorityqueue without causing exceptions.
/*            for (State s2 : candidate) {
                if (s2.currentCost >= minFullPath) {
                    candidate.poll();
                }
            }*/

        }
        return result;
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
