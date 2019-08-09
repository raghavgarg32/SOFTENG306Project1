package scheduler;

import graph.Graph;
import graph.Vertex;
import javafx.util.Pair;

import java.util.*;

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

    Processor(int processorNumber) {
        processorBlockHashMap = new HashMap<String, Integer>();
        this.processorNumber = processorNumber;
        processorBlockList = new ArrayList<>();
        processorVertexList = new ArrayList<Vertex>();

        startCost = 0;
        boundCost = 0;
        visited = new HashSet<>();
    }

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

    public Boolean isVertexInProcessor(Vertex vertex){
        return visited.contains(vertex);
    }


    public Vertex getLatestPreVertices(List<Vertex> prevVertices, HashMap<Vertex, Integer> prevVertexEndTimeHashMap) {
        Vertex latestPrevVertex = prevVertices.get(prevVertices.size() - 1);
        int latestPrevVertexEndTime = prevVertexEndTimeHashMap.get(latestPrevVertex);
        for (Vertex vertex : prevVertices){
            if (latestPrevVertexEndTime < prevVertexEndTimeHashMap.get(vertex)){
                latestPrevVertex = vertex;
                latestPrevVertexEndTime = prevVertexEndTimeHashMap.get(vertex);
            }
        }

        return latestPrevVertex;
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

    private List<Vertex> getDependedVerticesNotInProc(List<Vertex> verticesVIsDependedOn) {
        List<Vertex> dependedVerticesNotInProc = new ArrayList<Vertex>();

        for (Vertex vertixVIsDependedOn : verticesVIsDependedOn){
            if (!processorVertexList.contains(vertixVIsDependedOn)){
                dependedVerticesNotInProc.add(vertixVIsDependedOn);
            }
        }

        return dependedVerticesNotInProc;

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

    public int addVertex(Vertex v, List<Vertex> traversed, HashMap<Vertex, Integer> prevVertexEndTimeHashMap) {
        int startTime = 0;
        if (processorBlockList.size() != 0) {
            ProcessorBlock lastProcessorBlock = processorBlockList.get(processorBlockList.size() - 1);
            startTime = lastProcessorBlock.getEndTime();
        }

        List<Vertex> verticesVIsDependedOn = getPrevVertices(v, traversed);

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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Processor processor = (Processor) o;
        return Objects.equals(processorBlockList, processor.processorBlockList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(processorBlockList);
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

    public List<ProcessorBlock> getProcessorBlockList() {
        return processorBlockList;
    }
}
