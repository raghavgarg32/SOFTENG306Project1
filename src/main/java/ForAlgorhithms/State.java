package ForAlgorhithms;

import Graph.Graph;
import Graph.Vertex;
import Graph.Edge;

import java.util.*;

/**
 * Class to represent a schedule
 */
public class State {
    List<Processor> processors;
    int currentCost;
    int currentLevel;
    int costToBottomLevel;
    Graph g;

    List<Vertex> traversed;
    PriorityQueue<Vertex> toTraverse;


    public State(int numProcessors, Graph g) {
        traversed = new ArrayList<>();
        processors = new ArrayList<>();
        this.g = g;
        for (int i = 0; i < numProcessors; i++) {
            processors.add(new Processor());
        }
        toTraverse = new PriorityQueue<>(new VertexComparator());
        toTraverse.addAll(g.getRoots());
        currentLevel = 0;
        currentCost = g.getGreatestCost();
    }

    private State(State copyState) {
        traversed = new ArrayList<>();
        traversed.addAll(copyState.traversed);
        //traversed.addAll(copyState.toTraverse);
        //traversed.removeAll(copyState.toTraverse);
        processors = new ArrayList<>();
        this.g = copyState.g;
        for (int i = 0; i < copyState.processors.size(); i++) {
            processors.add(new Processor(copyState.processors.get(i)));
        }
        toTraverse = new PriorityQueue<>(new VertexComparator());
        toTraverse.addAll(copyState.toTraverse);
        currentLevel = copyState.currentLevel;
        currentCost = copyState.currentCost;
    }

    public State addVertex(int processorNum, Vertex v) {
        // Clone state then add the new vertex. Will also have to clone the processor list and processor block
        // list within it -> reference disappears once u clone so must use int
        State result = new State(this);
        result.toTraverse.remove(v);

        // Add the vertex to processor x, at the earliest possible time.
        result.processors.get(processorNum).addVertex(v, result.traversed);
        // Set the new currentCost && current level
        result.currentLevel = currentLevel + 1;

        for (Processor p : result.processors) {
            if (p.boundCost > result.currentCost) {
                //result.currentCost += v.getCost();
                result.currentCost = p.boundCost;
            }
        }
        // Update the toTraverseList with new vertexes to travers
        for (Edge e : v.getOutgoingEdges()) {
            Vertex toAdd = e.getToVertex();
            if (!result.toTraverse.contains(toAdd) && !result.traversed.contains(toAdd)) {
                result.toTraverse.add(toAdd);
            }
        }

        // Required to check for duplicates later.
        Collections.sort(result.processors);
        //System.out.println(result);

        return result;
    }

    public boolean canVisit(Vertex v) {
        //Vertex / Edges to be update to have the from vertices f
        //TODO
        return v.canVisit(traversed);
    }

    public boolean allVisited() {
        //Checks if any more vertexes exist to expand
        return toTraverse.isEmpty();
    }

    public HashSet<State> generatePossibilities() {
        //Generates a list of possible states to visit
        HashSet<State> possibleStates = new HashSet<>();
        if (!allVisited()) {
            List<Vertex> toAddList = new ArrayList<>();
            for (Vertex v : toTraverse) {
                if (canVisit(v)) {
//                    toTraverse.poll();
                    traversed.add(v);
                    for (int i = 0; i < processors.size(); i++) {
                        possibleStates.add(addVertex(i, v));
                    }
                }
            }
            toTraverse.addAll(toAddList);
            toTraverse.removeAll(traversed);
        }

        return possibleStates;

    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return toString().equals(o.toString());
    }

    //TODO return a copy of State, fpr a;; addVertex here.
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < processors.size(); i++) {
            Processor p = processors.get(i);
            sb.append(" Processor" + i + " " + p.toString() );
        }
        return sb.toString() +toTraverse;
    }
}
