package ForAlgorhithms;

import Graph.Vertex;

import java.util.List;
import java.util.PriorityQueue;

public class State {
    List<Processor> processors;
    int currentCost;
    int currentLevel;
    int costToBottomLevel;

    public State(){


    }

    public void setUpProcessors(int numOfProc){
        for (int i = 0 ; i < numOfProc; i++){
            processors.add(new Processor());
        }

    }

    public void setProcessors(List<Processor> processors) {
        this.processors = processors;
    }

    public void setCurrentCost(int currentCost) {
        this.currentCost = currentCost;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public void setCostToBottomLevel(int costToBottomLevel) {
        this.costToBottomLevel = costToBottomLevel;
    }

    public void setTraversed(List<Vertex> traversed) {
        this.traversed = traversed;
    }

    public void setToTraversed(List<Vertex> toTraversed) {
        this.toTraversed = toTraversed;
    }

    List<Vertex> traversed;
    List<Vertex> toTraversed;

    public List<Processor> getProcessors() {
        return processors;
    }

    public int getCurrentCost() {
        return currentCost;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public int getCostToBottomLevel() {
        return costToBottomLevel;
    }

    public List<Vertex> getTraversed() {
        return traversed;
    }

    public List<Vertex> getToTraversed() {
        return toTraversed;
    }
}
