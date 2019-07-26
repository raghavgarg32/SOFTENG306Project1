package script;

import java.util.HashMap;

public class Graph {

    private String _name;
    private HashMap<String, Vertex> _vertexMap;
    private HashMap<String, Edge> _edgeMap;


    public Graph(String name){
        _name = name;
        _vertexMap = new HashMap<String, Vertex>();
        _edgeMap = new HashMap<String, Edge>();
    }

    public void addVertex(String key, Vertex vertex){
        _vertexMap.put(key, vertex);
    }

    public void addEdge(String key, Edge edge){
        _edgeMap.put(key, edge);
    }

    public Vertex getVertex(String key){
        return _vertexMap.get(key);
    }

    public Edge getEdge(String key){
        return _edgeMap.get(key);
    }
}
