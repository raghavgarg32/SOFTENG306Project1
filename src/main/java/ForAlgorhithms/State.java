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
    // int currentCost;
    int currentLevel;
    int costToBottomLevel;
    Graph g;

    List<Vertex> visited;
    PriorityQueue<Vertex> candidate;


    public State(int numProcessors, Graph g) {
        visited = new ArrayList<>();
        processors = new ArrayList<>();
        this.g = g;
        for (int i = 0; i < numProcessors; i++) {
            processors.add(new Processor());
        }
        candidate = new PriorityQueue<>(new VertexComparator());
        candidate.addAll(g.getRoots());
        currentLevel = 0;
        costToBottomLevel = g.calculateBottomLevel();
    }

    private State(State copyState) {
        visited = new ArrayList<>();
        visited.addAll(copyState.visited);
        processors = new ArrayList<>();
        this.g = copyState.g;
        for (int i = 0; i < copyState.processors.size(); i++) {
            processors.add(new Processor(copyState.processors.get(i)));
        }
        candidate = new PriorityQueue<>(new VertexComparator());
        candidate.addAll(copyState.candidate);
        currentLevel = copyState.currentLevel;
        costToBottomLevel = copyState.costToBottomLevel;
    }

    private int calculateBottomLevel() {
        for (int i = 1; i < processors.size(); i++) {
            Processor prev = processors.get(i - 1);
            Processor p = processors.get(i);
            costToBottomLevel = Math.max(prev.boundCost, p.boundCost);
        }
        return costToBottomLevel;
    }

    private boolean updateLists(Vertex v) {
        visited.add(v);
        candidate.remove(v);

        for (Edge e : v.getOutgoingEdges()) {
            Vertex toAdd = e.getToVertex();
            if (!candidate.contains(toAdd) && !visited.contains(toAdd)) {
                candidate.add(toAdd);
            }
        }
        return true;
    }

    public State addVertex(int processorNum, Vertex v) {
        //Update the current list of vertices
        updateLists(v);

        //ScheduleVertex //TODO refactor to schedule Task, Maybe call Vertex Task
        processors.get(processorNum).addVertex(v, visited);

        currentLevel++;
        calculateBottomLevel();

        // Required to check for duplicates later.
        Collections.sort(processors);

        return this;
    }

    public boolean canVisit(Vertex v) {
        //Vertex / Edges to be update to have the from vertices f
        //TODO
        return v.canVisit(visited);
    }

    public boolean allVisited() {
        //Checks if any more vertexes exist to expand
        return candidate.isEmpty();
    }

    public HashSet<State> generatePossibilities() {
        //Generates a list of possible states to visit
        //TODO implement hashcode so the hashset actually functions as a hashset
        HashSet<State> possibleStates = new HashSet<>();
        if (!allVisited()) {
            List<Vertex> visited = new ArrayList<>();
            for (Vertex v : candidate) {
                if (canVisit(v)) {
                    visited.add(v);
                    for (int i = 0; i < processors.size(); i++) {
                        State toAdd = new State(this);
                        possibleStates.add(toAdd.addVertex(i, v));
                    }
                }
            }
            candidate.addAll(visited);
            candidate.removeAll(visited);
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
        sb.append("\nCurrent Level: " + currentLevel + " Bottom Level: " + costToBottomLevel);
        for (int i = 0; i < processors.size(); i++) {
            Processor p = processors.get(i);
            sb.append("\nProcessor " + i + ":" + p.toString());
        }
        return sb.toString() + "\nVerticies Left:" + candidate;
    }
}
