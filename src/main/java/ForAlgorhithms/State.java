package ForAlgorhithms;

import Graph.Vertex;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class State {
    List<Processor> processors;
    int currentCost;
    int currentLevel;
    int costToBottomLevel;

    List<Vertex> traversed;
    List<Vertex> toTraverse;

    public State addVertex(int processor, Vertex v){
        //TODO add the vertex to processor x, at the earliest possible time.
        return null;
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

//    public List<State> generatePossibilities() {
//        //Generates a list of possible states to visit
//        List<State> possibleStates = new ArrayList<>();
//        if(!allVisited()) {
//            for (Vertex v : toTraverse) {
//                if (canVisit(v)) {
//                    for (Processor p : processors) {
//                        possibleStates.add(addVertex(p, v));
//                    }
//                }
//            }
//        }
//        return possibleStates;
//
//    }
    //TODO return a copy of State, fpr a;; addVertex here.
}
