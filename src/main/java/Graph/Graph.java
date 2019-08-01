package Graph;

import java.util.HashMap;

public class Graph {

    private int greatestCost;
    private String name;
    private HashMap<String, Vertex> vertexHashMap;
    private HashMap<String, Edge> edgeHashMap;

    public Graph(String name) {
        this.name = name;
        vertexHashMap = new HashMap<>();
        edgeHashMap = new HashMap<>();
        greatestCost = Integer.MIN_VALUE;
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
        for (Vertex v : vertexHashMap.values()) {
            int bottomLevel = v.calculateBottomLevel();

            if (bottomLevel > greatestCost) {
                greatestCost = bottomLevel;
            }
        }
        return true;
    }

    public int getGreatestCost() {
        return greatestCost;
    }

    @Override
    public String toString() {
        return vertexHashMap.toString();
    }
}
