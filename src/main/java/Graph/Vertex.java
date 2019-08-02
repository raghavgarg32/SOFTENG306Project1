package Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Vertex {
    private String id;
    private int cost;
    private List<Edge> outgoingEdges;
    private List<Edge> incomingEdges;
    private List<Vertex> incomingVerticies;
    int bottomLevel;
    int level;



    public int getLevel() {
        return level;
    }

    public Vertex(String id, int cost) {
        this.id = id;
        this.cost = cost;
        outgoingEdges = new ArrayList<>();
        incomingEdges = new ArrayList<>();
        incomingVerticies = new ArrayList<>();
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

    public boolean containsIncomingVertex(Vertex v) {
        return incomingVerticies.contains(v);
    }

    public int getBottomLevel() {
        return bottomLevel;
    }

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
        System.out.println(currentVertex.id + "'s Bottom Level is " + currentVertex.bottomLevel);
    }

    public void addIncomingEdge(Edge edge) {
        incomingEdges.add(edge);
        incomingVerticies.add(edge.getFromVertex());
    }

    public boolean canVisit(List<Vertex> vList) {
        return incomingVerticies.containsAll(vList);
    }


    public void addOutgoingEdge(Edge edge) {
        outgoingEdges.add(edge);
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

    @Override
    public String toString() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        return id.equals(obj.toString());
    }
}
