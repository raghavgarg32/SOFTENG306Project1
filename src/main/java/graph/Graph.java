package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<Vertex> getRoots() {
        List<Vertex> result = new ArrayList<>();
        for (Vertex vertex : vertexHashMap.values()) {
            if (vertex.isRoot()) {
                result.add(vertex);
            }
        }
        return result;
    }

    public Edge getEdge(String key) {
        return edgeHashMap.get(key);
    }

    public int calculateBottomLevel() {
        //for (Vertex v : vertexHashMap.values()) {
          //  v.calculateBottomLevel();
        //}
        //return -1;
        Map.Entry<String,Vertex> entry = vertexHashMap.entrySet().iterator().next();
        // Get root vertex
        String key = entry.getKey();
        int bottomLevel =  vertexHashMap.get(key).calculateBottomLevel();
        if(bottomLevel > greatestCost){
            greatestCost = bottomLevel;
        }
        return greatestCost;
    }

    public int getGreatestCost() {
        return greatestCost;
    }

    @Override
    public String toString() {
        return vertexHashMap.toString() + edgeHashMap.toString();
    }
}
