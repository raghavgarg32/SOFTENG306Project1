package scheduler;

import graph.Graph;
import graph.Vertex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class to represent one CPU/processor on a schedule
 */
public class Processor implements Comparable<Processor> {
    List<ProcessorBlock> processorBlockList;
    HashMap<String,Integer> processorBlockHashMap;
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
    }

    public Processor(Processor toCopy) {

        processorBlockList = new ArrayList<>();
        processorBlockHashMap = new HashMap<String, Integer>();
        startCost = toCopy.startCost;
        boundCost = toCopy.boundCost;
        processorBlockList.addAll(toCopy.processorBlockList);
        processorBlockHashMap = toCopy.processorBlockHashMap;
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
        for (ProcessorBlock block : processorBlockList){
            if (block.getV() == vertex){
                return  true;
            }
        }
        return false;
    }


    public int addVertex(Vertex v, List<Vertex> traversed, int prevEndTime) {
        int startTime = 0;
        if (processorBlockList.size() != 0) {
            ProcessorBlock lastProcessorBlock = processorBlockList.get(processorBlockList.size() - 1);
            startTime = lastProcessorBlock.getEndTime();
        }

        Vertex lastVertex = null;
        List<Vertex> prevVertices = getPrevVertices(v, traversed);
        if (prevVertices.size() > 0 ){
            lastVertex = prevVertices.get(prevVertices.size() - 1);
            //System.out.println("prev " + lastVertex);

        }

        int costTillNow;

        if (lastVertex != null){
            costTillNow = processorBlockHashMap.get(lastVertex.getId());
            //System.out.println("cost " + costTillNow);

        }


        int comCost = 0;

        Boolean isOnDiffProcessor = true;
        if (traversed.size() > 1 && processorBlockList.size() == 0) {

            costTillNow = prevEndTime;
            //System.out.println("cost " + costTillNow);
            comCost = v.getEdgeWeightFrom(lastVertex);
            startTime = costTillNow + comCost;
        } else if (traversed.size() > 1 && processorBlockList.size() > 0){
            if (!isVertexInProcessor(traversed.get(traversed.size() - 2))) {
                costTillNow = prevEndTime;
                comCost = v.getEdgeWeightFrom(lastVertex);
                startTime = costTillNow + comCost;
                //System.out.println("cost " + costTillNow);
            }
        }  else {
            isOnDiffProcessor = false;
        }


        //TODO plz go over this. Need to check whether or not the communication cost need to be added.
        //Value of lowest edge from any previous vertex
//        int comCost = Integer.MAX_VALUE;
//        if (v.isRoot()) {
//            comCost = 0;
//        }
//
//        for (ProcessorBlock processorBlock : processorBlockList) {
//            Vertex v1 = processorBlock.getV();
//            if (v.containsIncomingVertex(v1)) {
//                comCost = 0;
//                break;
//            }
//        }
//        if (comCost != 0) {
//            //Out of all the other incoming verticies from the other processors, grab the lowest edge weight.
//
//            for (Vertex v1 : traversed) {
//                if (v.containsIncomingVertex(v1)) {
//                    //TODO calculate start time
//                    //Calculate start time
//                    int tempComCost = v.getEdgeWeightFrom(v1);
//                    if (tempComCost < comCost) {
//                        comCost = tempComCost;
//                    }
//                }
//            }
//        }
//        startTime += comCost;
        ProcessorBlock newProcBlock = new ProcessorBlock(v, startTime);
        processorBlockList.add(newProcBlock);
        processorBlockHashMap.put(v.getId(),newProcBlock.getEndTime());

        //System.out.println(Arrays.asList(processorBlockHashMap));

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
