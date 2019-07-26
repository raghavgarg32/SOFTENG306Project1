package script;

public class Edge {

    private Vertex _fromVertex;
    private Vertex _toVertex;
    private int _switchCost;

    private Edge(int cost, Vertex fromVertex, Vertex toVertex){
        _fromVertex = fromVertex;
        _toVertex = toVertex;
        _switchCost = cost;
    }

    public static Edge createEdge(int cost, Vertex fromVertex, Vertex toVertex){
        Edge edge = new Edge(cost, fromVertex, toVertex);
        fromVertex.addOutgoingEdge(edge);
        return edge;
    }
}
