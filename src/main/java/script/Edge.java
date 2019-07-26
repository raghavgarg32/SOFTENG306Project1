package script;

public class Edge {

    private Vertex _fromVertex;
    private Vertex _toVertex;
    private int _switchCost;

    public Edge(int cost, Vertex fromVertex, Vertex toVertex){
        _fromVertex = fromVertex;
        _toVertex = toVertex;
        _switchCost = cost;
    }
}
