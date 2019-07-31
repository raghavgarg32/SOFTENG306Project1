package ForAlgorhithms;

import Graph.Vertex;

import java.util.List;
import java.util.PriorityQueue;

public class State {
    List<Processor> processors;
    int currentCost;
    int currentLevel;
    int costToBottomLevel;

    List<Vertex> traversed;
    List<Vertex> toTraversed;

    public State addVertex(int processor, Vertex v){
        //TODO add the vertex to processor x, at the earliest possible time.
        return null;
    }
    public boolean canVisit(Vertex v){
        //Vertex / Edges to be update to have the from vertices f
        return false;
    }
    public boolean allVisited(){
        //Instance of the graph, check if all vertices exist within traversed.
        return false;
    }
    //TODO return a copy of State, fpr a;; addVertex here.
}
