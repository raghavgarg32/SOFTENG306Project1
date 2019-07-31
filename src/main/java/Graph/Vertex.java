package Graph;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    private String id;
    private int cost;
    private List<Edge> outgoingEdges;
    int bottomLevel;

    public Vertex(String id, int cost){
        this.id = id;
        this.cost = cost;
        outgoingEdges = new ArrayList<>();
    }

    private List<Vertex> visited;
    //TODO calculate bottom level. //DFS but prioritise most expensive
    public void calculateBottomLevel() {
        visited = new ArrayList<Vertex>();
        dfs();
    }

    private void dfs(){
        visited.add(this);
        for (int i = 0; i < outgoingEdges.size(); i++){

        }
    }

    public void addOutgoingEdge(Edge edge){
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
        return cost + outgoingEdges.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return id.equals(obj.toString());
    }
}
