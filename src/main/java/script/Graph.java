package script;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

public class Graph {

    public static Graph graph;
    private String name;
    private HashMap<String, Vertex> vertexHashMap;
    private HashMap<String, Edge> edgeHashMap;


     Graph(String name){
        this.name = name;
        vertexHashMap = new HashMap<String, Vertex>();
        edgeHashMap = new HashMap<String, Edge>();
    }

    public void addVertex(String key, Vertex vertex){
        vertexHashMap.put(key, vertex);
    }

    public void addEdge(String key, Edge edge){
        edgeHashMap.put(key, edge);
    }

    public Vertex getVertex(String key){
        return vertexHashMap.get(key);
    }

    public Edge getEdge(String key){
        return edgeHashMap.get(key);
    }

}
