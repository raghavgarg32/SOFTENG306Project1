package ForAlgorhithms;

import Graph.Vertex;

import java.util.List;

/**
 * Class to represent one CPU/processor on a schedule
 */
public class Processor implements Comparable<Processor> {
    List<ProcessorBlock> processorBlockList;
    Vertex startVertex;

    int lastCost;
    int startCost;

    public int addVertex(Vertex v){
        //TODO add vertex to processor block list
        //TODO Return new earliest start time + bottomLevel aka our bound level.
        return 0;
    }

    @Override
    public int compareTo(Processor processor) {
        int result = this.startCost - processor.startCost;
        if (result == 0) {
            result = this.startVertex.getId().compareTo(processor.startVertex.getId());
        }
        return result;
    }

    @Override
    public String toString() {
        return startCost + processorBlockList.toString();
    }
}
