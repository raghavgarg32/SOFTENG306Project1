package ForAlgorhithms;

import Graph.Graph;
import Graph.Vertex;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to represent one CPU/processor on a schedule
 */
public class Processor implements Comparable<Processor> {
    List<ProcessorBlock> processorBlockList;
    Vertex startVertex;
    Graph g;

    int boundCost;
    int startCost;

    Processor() {
        processorBlockList = new ArrayList<>();
        startCost = 0;
        boundCost = 0;
    }

    public int addVertex(Vertex v) {
        ProcessorBlock lastProcessorBlock = processorBlockList.get(processorBlockList.size() - 1);
        int startTime = 0;
        if (lastProcessorBlock != null) {
            startTime = lastProcessorBlock.getEndTime();
        }
        //TODO plz go over this. Need to check whether or not the communication cost need to be added.
        int comCost = 0;
/*
        for (ProcessorBlock processorBlock : processorBlockList) {
            Vertex v1 = processorBlock.getV();
            if (v.containsIncomingVertex(v1)) {
                co = false;
                break;
            }
        }*/

        startTime += comCost;

        processorBlockList.add(new ProcessorBlock(v, startTime));
        boundCost = startTime + v.getBottomLevel();
        return boundCost;
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
