package scheduler;

import graph.Graph;
import graph.Vertex;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Class to represent one CPU/processor on a schedule
 */
public class Processor implements Comparable<Processor> {
    List<ProcessorBlock> processorBlockList;
    HashMap<String,Integer> processorBlockHashMap;
    HashSet<Vertex> visited;
    Vertex startVertex;
    Graph g;

    int processorNumber;

    int boundCost;
    int startCost;

    Processor(int processorNumber) {
        processorBlockHashMap = new HashMap<String, Integer>();
        this.processorNumber = processorNumber;
        processorBlockList = new ArrayList<>();
        startCost = 0;
        boundCost = 0;
        visited = new HashSet<>();
    }

    public Processor(Processor toCopy, int processorNumber) {
        this.processorNumber = processorNumber;
        processorBlockList = new ArrayList<>();
        processorBlockHashMap = new HashMap<String, Integer>();
        startCost = toCopy.startCost;
        boundCost = toCopy.boundCost;
        processorBlockList.addAll(toCopy.processorBlockList);
        processorBlockHashMap = toCopy.processorBlockHashMap;
        visited = new HashSet<>(toCopy.visited);
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

    public Boolean isVertexInProcessor(Vertex vertex){
        return visited.contains(vertex);
    }

    public Pair<Vertex,Integer> getLatestPreVertices(List<Vertex> prevVertices, HashMap<Vertex, Integer> prevVertexEndTimeHashMap) {
        Vertex latestPrevVertex = prevVertices.get(prevVertices.size() - 1);
        int latestPrevVertexEndTime = prevVertexEndTimeHashMap.get(latestPrevVertex);
        for (Vertex vertex : prevVertices){
            if (latestPrevVertexEndTime < prevVertexEndTimeHashMap.get(vertex)){
                latestPrevVertex = vertex;
                latestPrevVertexEndTime = prevVertexEndTimeHashMap.get(vertex);
            }
        }

        Pair<Vertex, Integer> latestPrevVertAndEndTime = new Pair<>(latestPrevVertex,latestPrevVertexEndTime);

        return latestPrevVertAndEndTime;

    }

    public int addVertex(Vertex v, List<Vertex> traversed, HashMap<Vertex, Integer> prevVertexEndTimeHashMap) {
        visited.add(v);
        int startTime = 0;
        if (processorBlockList.size() != 0) {
            ProcessorBlock lastProcessorBlock = processorBlockList.get(processorBlockList.size() - 1);
            startTime = lastProcessorBlock.getEndTime();
        }

        List<Vertex> prevVertices = getPrevVertices(v, traversed);
        if (prevVertices.size() > 0){

            Pair<Vertex, Integer> latestPrevVertAndEndTime = getLatestPreVertices(prevVertices,prevVertexEndTimeHashMap);

            Vertex latestPrevVertex = latestPrevVertAndEndTime.getKey();
            int latestPrevVertexEndTime = latestPrevVertAndEndTime.getValue();

            if (traversed.size() > 1 ){
                if (isVertexInProcessor(latestPrevVertex)) {
                    if (processorBlockList.size() > 0) {
                        ProcessorBlock lastProcessorBlock = processorBlockList.get(processorBlockList.size() - 1);
                        startTime = latestPrevVertexEndTime;
                        if (lastProcessorBlock.getEndTime() > startTime) {
                            startTime = lastProcessorBlock.getEndTime();
                        }
                    }

                } else if (!isVertexInProcessor(latestPrevVertex)){
                    int comCost = v.getEdgeWeightFrom(latestPrevVertex);
                    startTime = latestPrevVertexEndTime + comCost;
                    if (processorBlockList.size() > 0) {
                        ProcessorBlock lastProcessorBlock = processorBlockList.get(processorBlockList.size() - 1);
                        if (lastProcessorBlock.getEndTime() > startTime) {
                            startTime = lastProcessorBlock.getEndTime();
                        }
                    }
                }
            }

        }

        ProcessorBlock newProcBlock = new ProcessorBlock(v, startTime);
        processorBlockList.add(newProcBlock);

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

    public int getEndTime(){
        if (processorBlockList.size() > 0){
            return processorBlockList.get(processorBlockList.size() - 1).getEndTime();
        }
        return 0;

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

    public void outputFormat() {
        for (int i =0; i < processorBlockList.size(); i++){
            ProcessorBlock currentProcessorBlock = processorBlockList.get(i);
            Vertex currentVertex = currentProcessorBlock.getV();
            currentVertex.setStartTime(currentProcessorBlock.getStartTime());
            currentVertex.setProcessorNo(processorNumber);
        }

    }


}
