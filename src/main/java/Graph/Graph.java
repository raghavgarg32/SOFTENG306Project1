package Graph;

import java.util.HashMap;

public class Graph {

    private String name;
    private HashMap<String, Vertex> vertexHashMap;
    private HashMap<String, Edge> edgeHashMap;

    public Graph(String name) {
        this.name = name;
        vertexHashMap = new HashMap<>();
        edgeHashMap = new HashMap<>();
    }

    public void addVertex(String key, Vertex vertex) {
        vertexHashMap.put(key, vertex);
    }

    public void addEdge(String key, Edge edge) {
        edgeHashMap.put(key, edge);
    }

    public Vertex getVertex(String key) {
        return vertexHashMap.get(key);
    }

    public Edge getEdge(String key) {
        return edgeHashMap.get(key);
    }

    public boolean calculateBottomLevel() {
        for(Vertex v:vertexHashMap.values()){
            v.calculateBottomLevel();
        }
        return true;
    }

    @Override
    public String toString() {
        return vertexHashMap.toString();
    }
}
