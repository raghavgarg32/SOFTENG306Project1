package Graph;

import java.util.HashMap;
import java.util.Map;

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

    public int calculateBottomLevel() {
        Map.Entry<String,Vertex> entry = vertexHashMap.entrySet().iterator().next();
        // Get root vertex
        String key = entry.getKey();
        return vertexHashMap.get(key).calculateBottomLevel();
    }

    @Override
    public String toString() {
        return vertexHashMap.toString();
    }
}
