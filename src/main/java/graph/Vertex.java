package graph;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    private String id;
    private int cost;
    private List<Edge> outgoingEdges;
    private List<Edge> incomingEdges;
    private List<Vertex> incomingVerticies;
    private List<Vertex> outgoingVerticies;
    int bottomLevel;
    int level;

    public List<Vertex> getOutgoingVerticies() {
        return outgoingVerticies;
    }

    // These variables are for output parsing ONLY they are not changed during the execution of the algorithm
    int startTime;
    int processorNo;

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

   public List<Vertex> getCommonVertices(List<Vertex> vList){
        List<Vertex> common = new ArrayList<>(incomingVerticies);
        common.retainAll(vList);
        return common;
    }
    public void setProcessorNo(int processorNo) {
        this.processorNo = processorNo;
    }

    public int getLevel() {
        return level;
    }

   public  boolean isRoot() {
        return incomingEdges.size() == 0;
    }

    public Vertex(String id, int cost) {
        this.id = id;
        this.cost = cost;
        outgoingEdges = new ArrayList<>();
        incomingEdges = new ArrayList<>();
        incomingVerticies = new ArrayList<>();
        outgoingVerticies = new ArrayList<>();
        bottomLevel = -1;
        findLevel();
    }

    private int findLevel() {
        for (Vertex v : incomingVerticies) {
            if (v.level >= level) {
                level = v.level + 1;
            }
        }
        return level;
    }

    public List<Vertex> getIncomingVerticies() {
        return incomingVerticies;
    }

    public boolean containsIncomingVertex(Vertex v) {
        return incomingVerticies.contains(v);
    }

    //TODO implement hashmap
    public int getEdgeWeightFrom(Vertex v) {
        int cost =-1;
        for(Edge e:incomingEdges){
            if(e.getFromVertex() == v){
                cost = e.getSwitchCost();
            }
        }
        return cost;
    }

    //todo SHould just be the below function
    public int getBottomLevel() {
        return bottomLevel;
    }

    //TODO calculate bottom level. //DFS but prioritise most expensive
    public int calculateBottomLevel() {

        dfs(this);
        return bottomLevel;
    }


    private void dfs(Vertex currentVertex) {
        // if the bottom level of the current vertex cannot be used to calculate the bottom level
        if (currentVertex.outgoingEdges.size() == 0){
            currentVertex.bottomLevel = currentVertex.cost;
        } else {

            int highestBottomLevel = -1;

            //check bottom level of all next vertices
            for (int i = 0; i < currentVertex.outgoingEdges.size(); i++) {
                Vertex nextVertex = currentVertex.outgoingEdges.get(i).getToVertex();

                //if next bottom level is uninitialised
                if (nextVertex.bottomLevel == -1) {
                    dfs(nextVertex);
                }

                //check if next vertex is largest bottom level so far
                else if (nextVertex.bottomLevel > highestBottomLevel) {
                    highestBottomLevel = nextVertex.bottomLevel;
                }

            }

            currentVertex.bottomLevel = currentVertex.cost + highestBottomLevel;
        }
    }

    public void addIncomingEdge(Edge edge) {
        incomingEdges.add(edge);
        incomingVerticies.add(edge.getFromVertex());
    }

    public boolean canVisit(List<Vertex> vList) {
        return vList.containsAll(incomingVerticies);
    }


    public void addOutgoingEdge(Edge edge) {
        outgoingEdges.add(edge);
        outgoingVerticies.add(edge.getToVertex());
    }

    public String getId() {
        return id;
    }

    public int getCost() {
        return cost;
    }

    public List<Edge> getOutgoingEdges() {
        return outgoingEdges;
    }

    public String toStringSolution() {
        String output = id + "\t[Weight=" + cost + ",Start=" + startTime + ",Processor=" + processorNo +"];";
        return output;
    }

    @Override
    public String toString() {
        String output = id + "\t[Weight=" + cost + "];";
        return output;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this.toString().equals(obj.toString());
    }
}
