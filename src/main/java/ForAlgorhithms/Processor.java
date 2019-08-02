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

    public Processor(Processor toCopy) {

        processorBlockList = new ArrayList<>();
        startCost = toCopy.startCost;
        boundCost = toCopy.boundCost;
        processorBlockList.addAll(toCopy.processorBlockList);
    }

    public int addVertex(Vertex v, List<Vertex> traversed) {
        int startTime = 0;
        if (processorBlockList.size() != 0) {
            ProcessorBlock lastProcessorBlock = processorBlockList.get(processorBlockList.size() - 1);
            startTime = lastProcessorBlock.getEndTime();
        }
        //TODO plz go over this. Need to check whether or not the communication cost need to be added.
        //Value of lowest edge from any previous vertex
        int comCost = Integer.MAX_VALUE;
        if (v.isRoot()) {
            comCost = 0;
        }

        for (ProcessorBlock processorBlock : processorBlockList) {
            Vertex v1 = processorBlock.getV();
            if (v.containsIncomingVertex(v1)) {
                comCost = 0;
                break;
            }
        }
        if (comCost != 0) {
            //Out of all the other incoming verticies from the other processors, grab the lowest edge weight.

            for (Vertex v1 : traversed) {
                if (v.containsIncomingVertex(v1)) {
                    //TODO calculate start time
                    //Calculate start time
                    int tempComCost = v.getEdgeWeightFrom(v1);
                    if (tempComCost < comCost) {
                        comCost = tempComCost;
                    }
                }
            }
        }
        startTime += comCost;

        processorBlockList.add(new ProcessorBlock(v, startTime));
        boundCost = startTime + v.getBottomLevel();
        return boundCost;
    }

    @Override
    public int compareTo(Processor processor) {
        int result = this.startCost - processor.startCost;
        if (result == 0) {
            String id0 = "";
            String id1 = "";
            if (processorBlockList.size() != 0) {
                id0 = processorBlockList.get(0).getV().getId();
            }
            if (processor.processorBlockList.size() != 0) {
                id1 = processor.processorBlockList.get(0).getV().getId();
            }
            result = id0.compareTo(id1);
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        Processor p = (Processor) o;
        return this.toString().equals(p.toString());
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public String toString() {
        return processorBlockList.toString();
    }
}
