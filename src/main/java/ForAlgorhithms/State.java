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


}
