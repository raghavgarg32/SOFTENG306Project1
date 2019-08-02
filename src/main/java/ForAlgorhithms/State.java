package ForAlgorhithms;

import Graph.Graph;
import Graph.Vertex;
import Graph.Edge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

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
        currentLevel = 0;
        currentCost = g.getGreatestCost();
    }

    public State addVertex(int processorNum, Vertex v) {
        // Clone state then add the new vertex. Will also have to clone the processor list and processor block
        // list within it -> reference disappears once u clone so must use int
        State result = new State(processorNum, this.g);
        result.traversed = this.traversed;
        result.toTraverse = this.toTraverse;
        result.processors = this.processors;

        // Add the vertex to processor x, at the earliest possible time.
        result.processors.get(processorNum).addVertex(v);
        // Set the new currentCost && current level
        result.currentLevel = v.getLevel();
        result.currentCost += v.getCost();
        // Update the toTraverseList with new vertexes to travers
        for(Edge e : v.getOutgoingEdges()) {
            toTraverse.add(e.getToVertex());
        }

        // Required to check for duplicates later.
        Collections.sort(result.processors);

        return result;
    }
    public boolean canVisit(Vertex v){
        //Vertex / Edges to be update to have the from vertices f
        //TODO
        return v.canVisit(traversed);
    }
    public boolean allVisited(){
        //Checks if any more vertexes exist to expand
        return toTraverse.isEmpty();
    }

    public List<State> generatePossibilities() {
        //Generates a list of possible states to visit
        List<State> possibleStates = new ArrayList<>();
        if (!allVisited()) {
            for (Vertex v : toTraverse) {
                if (canVisit(v)) {
                    toTraverse.poll();
                    traversed.add(v);
                    for (int i = 0; i < processors.size(); i++) {
                        possibleStates.add(addVertex(i, v));
                    }
                }
            }
        }

        return possibleStates;

    }
    //TODO return a copy of State, fpr a;; addVertex here.

    @Override
    public String toString() {
        return processors.toString();
    }
}
