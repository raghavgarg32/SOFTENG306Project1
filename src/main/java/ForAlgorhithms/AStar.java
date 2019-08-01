package ForAlgorhithms;

import Graph.Graph;

import java.util.PriorityQueue;

public class AStar {
    int minFullPath = Integer.MAX_VALUE;
    boolean traversed;
    PriorityQueue<State> candidate;
    PriorityQueue<State> visited;
    //PriorityQueue<State> states = new PriorityQueue<>();

    Graph graph;

    public AStar(Graph graph) {
        candidate = new PriorityQueue<>(new AStarComparator());
        visited = new PriorityQueue<>(new AStarComparator());
        this.graph = graph;
        traversed = false;

        //Todo implement state with root vertex;
        candidate.add(new State(1,graph));
    }

//    public void runAlgorhithm() {
//        while (!candidate.isEmpty()) {
//            State s = candidate.poll();
//            for (State s1 : s.generatePossibilities()) {
//                if (s1.currentCost < minFullPath) {
//                    candidate.add(s1);
//                    if (s1.allVisited()) {
//                        minFullPath = s1.currentCost;
//                    }
//                }
//            }
//
//            for (State s2 : candidate) {
//                if (s2.currentCost >= minFullPath) {
//                    candidate.poll();
//                }
//            }
//
//        }
//    }

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
