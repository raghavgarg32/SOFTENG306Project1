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
    int lastProcessorVertexAddedTo;
    private int prevProcessNum = -1;


    public State(int numProcessors, Graph g) {
        traversed = new ArrayList<>();
        processors = new ArrayList<>();
        this.g = g;
        for (int i = 0; i < numProcessors; i++) {
            processors.add(new Processor(i));
        }
        toTraverse = new PriorityQueue<>(new VertexComparator());
        toTraverse.addAll(g.getRoots());
        currentLevel = 0;
        costToBottomLevel = g.getGreatestCost();
        currentCost = 0;
    }

    private State(State copyState) {
        traversed = new ArrayList<>();
        traversed.addAll(copyState.traversed);
        processors = new ArrayList<>();
        this.g = copyState.g;
        for (int i = 0; i < copyState.processors.size(); i++) {
            processors.add(new Processor(copyState.processors.get(i)));
        }
        toTraverse = new PriorityQueue<>(new VertexComparator());
        toTraverse.addAll(copyState.toTraverse);
        currentLevel = copyState.currentLevel;
        costToBottomLevel = copyState.costToBottomLevel;

    }

    public List<Vertex> getPrevVertices(Vertex v, List<Vertex> traversed){
        List<Vertex> prevVertices = new ArrayList<>();

        for (Vertex v1 : traversed) {
            if (v.containsIncomingVertex(v1)) {
                prevVertices.add(v1);
            }
        }
        return prevVertices;
    }

    public State addVertex(int processorNum, Vertex v) {
        // Clone state then add the new vertex. Will also have to clone the processor list and processor block
        // list within it -> reference disappears once u clone so must use int
        State result = new State(this);
        result.lastProcessorVertexAddedTo = processorNum;
        result.traversed.add(v);
        result.toTraverse.remove(v);

        List<Vertex> prevVertices = getPrevVertices(v, traversed);

        //System.out.println(result.processors);



        Vertex lastVertex;
        int endTime= 0;
        if (prevVertices.size() > 0 ){
            lastVertex = prevVertices.get(prevVertices.size() - 1);
            for (Processor processor : result.processors) {
                //System.out.println("hash " + processor);
                for (ProcessorBlock block : processor.processorBlockList) {
                    if (block.getV() == lastVertex) {
                        endTime = block.getEndTime();
                    }

                }
            }
            //System.out.println("end " + endTime);
        }



        List<ProcessorBlock> hasBlock = new ArrayList<>();
        if (prevProcessNum != -1){
            hasBlock = result.processors.get(prevProcessNum).processorBlockList;
        }

        //System.out.println(Arrays.toString(hasBlock.toArray()));
        // Add the vertex to processor x, at the earliest possible time.
        result.processors.get(processorNum).addVertex(v, result.traversed, endTime);

        prevProcessNum = processorNum;
        // Set the new currentCost && current level
        result.currentLevel = currentLevel + 1;

        int stateEndTime = 0;

        for (Processor p : result.processors) {
            if (p.boundCost > result.costToBottomLevel) {
                //result.currentCost += v.getCost();
                result.costToBottomLevel = p.boundCost;
            }

            if (stateEndTime < p.getEndTime()){
                stateEndTime = p.getEndTime();
            }

        }

        result.currentCost = stateEndTime;

        // Update the toTraverseList with new vertexes to travers
        for (Edge e : v.getOutgoingEdges()) {
            Vertex toAdd = e.getToVertex();
            if (!result.toTraverse.contains(toAdd) && !result.traversed.contains(toAdd)) {
                result.toTraverse.add(toAdd);
            }
        }

        // Required to check for duplicates later.
        //Collections.sort(result.processors);
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
        //TODO implement hashcode so the hashset actually functions as a hashset
        HashSet<State> possibleStates = new HashSet<>();
        List<State> possibleStatesList = new ArrayList<>();
        if (!allVisited()) {
            List<Vertex> toAddList = new ArrayList<>();
            for (Vertex v : toTraverse) {
                if (canVisit(v)) {
                    toAddList.add(v);
                    for (int i = 0; i < processors.size(); i++) {
                        possibleStates.add(addVertex(i, v));
                        possibleStatesList.add(addVertex(i, v));
                    }
                }
            }
            toTraverse.addAll(toAddList);
            toTraverse.removeAll(toAddList);
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
        sb.append("\nCurrent Level: " + currentLevel + " Bottom Level: " + costToBottomLevel + " Current Cost: " + currentCost);
        for (int i = 0; i < processors.size(); i++) {
            Processor p = processors.get(i);
            sb.append("\nProcessor " + i + ":" + p.toString());
        }
        return sb.toString() + "\nVerticies Left:" + toTraverse;
    }
}
