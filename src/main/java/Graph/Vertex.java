package Graph;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    private String id;
    private int cost;
    private List<Edge> outgoingEdges;
    private List<Edge> incomingEdges;
    private List<Vertex> incomingVerticies;
    int bottomLevel;

    public Vertex(String id, int cost) {
        this.id = id;
        this.cost = cost;
        outgoingEdges = new ArrayList<>();
        incomingEdges = new ArrayList<>();
        incomingVerticies = new ArrayList<>();
    }


    //TODO calculate bottom level. //DFS but prioritise most expensive
    public int calculateBottomLevel() {
        bottomLevel = -1;
        dfs(this, 0);
        return bottomLevel;
    }


    private void dfs(Vertex currentVertex, int currentCost) {
        currentCost = currentCost + currentVertex.cost;

        if (currentVertex.outgoingEdges.size() == 0) {
            if (currentCost > bottomLevel) {
                bottomLevel = currentCost;
               // System.out.println(bottomLevel);
            }

        } else {

            for (int i = 0; i < currentVertex.outgoingEdges.size(); i++) {
                Vertex nextVertex = currentVertex.outgoingEdges.get(i).getToVertex();
                dfs(nextVertex, currentCost);

            }
        }
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
