package ForAlgorhithms;

import Graph.Graph;
import Graph.Vertex;
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
    List<Vertex> processorVertexList;

    HashMap<String,Integer> processorBlockHashMap;
    HashSet<Vertex> visited;
    Vertex startVertex;
    Graph g;

    int processorNumber;
    int boundCost;
    int startCost;

    /**
     * Setting up the fields of the Processor with initial values
     */
    Processor(int processorNumber) {
        processorBlockHashMap = new HashMap<String, Integer>();
        this.processorNumber = processorNumber;
        processorBlockList = new ArrayList<>();
        processorVertexList = new ArrayList<Vertex>();

        startCost = 0;
        boundCost = 0;
        visited = new HashSet<>();
    }

    /**
     * Create a copy a given processor
     */
    public Processor(Processor toCopy, int processorNumber) {
        this.processorNumber = processorNumber;
        processorBlockList = new ArrayList<>();
        processorVertexList = new ArrayList<>();
        processorBlockHashMap = new HashMap<String, Integer>();
        startCost = toCopy.startCost;
        boundCost = toCopy.boundCost;
        processorBlockList.addAll(toCopy.processorBlockList);
        processorVertexList.addAll(toCopy.processorVertexList);
        processorBlockHashMap = toCopy.processorBlockHashMap;
        visited = new HashSet<>(toCopy.visited);
    }

    public List<Vertex> getVerticesVDependsOn(Vertex v, List<Vertex> traversed){
        List<Vertex> prevVertices = new ArrayList<>();

        for (Vertex v1 : traversed) {
            if (v.containsIncomingVertex(v1)) {
                prevVertices.add(v1);
            }
        }
        return prevVertices;
    }

    public Boolean isVertexInProcessor(Vertex vertex){
        for (ProcessorBlock block : processorBlockList){
            if (block.getV() == vertex){
                return  true;
            }
        }
        return false;
    }


    public int addVertex(Vertex v, List<Vertex> traversed, HashMap<Vertex, Integer> prevVertexEndTimeHashMap) {
        int startTime = 0;
        if (processorBlockList.size() != 0) {
            ProcessorBlock lastProcessorBlock = processorBlockList.get(processorBlockList.size() - 1);
            startTime = lastProcessorBlock.getEndTime();
        }

        List<Vertex> verticesVIsDependedOn = getVerticesVDependsOn(v, traversed);

        List<Vertex> dependedVerticesNotInProc = getDependedVerticesNotInProc(verticesVIsDependedOn);


        if (dependedVerticesNotInProc.size() > 0) {
            int timeOfStart = getStartTime(v, dependedVerticesNotInProc, prevVertexEndTimeHashMap);
            if (processorBlockList.size() != 0) {
                ProcessorBlock lastProcessorBlock = processorBlockList.get(processorBlockList.size() - 1);
                if (lastProcessorBlock.getEndTime() > timeOfStart){
                    startTime = lastProcessorBlock.getEndTime();
                } else {
                    startTime = timeOfStart;
                }
            } else{
                startTime = timeOfStart;
            }

        }


        ProcessorBlock newProcBlock = new ProcessorBlock(v, startTime);
        processorBlockList.add(newProcBlock);
        processorVertexList.add(v);

        boundCost = startTime + v.getBottomLevel();
        return boundCost;
    }

    private int getStartTime(Vertex v, List<Vertex> dependedVerticesNotInProc, HashMap<Vertex,Integer> prevVertexEndTimeHashMap) {
        int maxStartTime = 0;

        for (Vertex dependedVertex : dependedVerticesNotInProc){
            int finishTimeOfDependedVertex = prevVertexEndTimeHashMap.get(dependedVertex);
            int communicationCost = v.getEdgeWeightFrom(dependedVertex);
            int possibleStartTime = communicationCost + finishTimeOfDependedVertex;

            if (possibleStartTime > maxStartTime){
                maxStartTime = possibleStartTime;
            }
        }

        return maxStartTime;

    }

    private List<Vertex> getDependedVerticesNotInProc(List<Vertex> verticesVIsDependedOn) {
        List<Vertex> dependedVerticesNotInProc = new ArrayList<Vertex>();

        for (Vertex vertixVIsDependedOn : verticesVIsDependedOn){
            if (!processorVertexList.contains(vertixVIsDependedOn)){
                dependedVerticesNotInProc.add(vertixVIsDependedOn);
            }
        }

        return dependedVerticesNotInProc;

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
}
