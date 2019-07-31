package Graph;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    private String id;
    private int cost;
    private List<Edge> outgoingEdges;
    private List<Vertex> paths;
    int bottomLevel = 0;

    public Vertex(String id, int cost){
        this.id = id;
        this.cost = cost;
        outgoingEdges = new ArrayList<>();
    }


    //TODO calculate bottom level. //DFS but prioritise most expensive
    public int calculateBottomLevel() {
        paths = new ArrayList<Vertex>();
        dfs(this);
        return bottomLevel;
    }

    private void dfs(Vertex v){
        bottomLevel+=this.cost;
        if (outgoingEdges.size() == 0) {
            paths.add(this);
        }
        for (int i = 0; i < outgoingEdges.size(); i++){
            dfs(v.outgoingEdges.get(i).getToVertex());
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
