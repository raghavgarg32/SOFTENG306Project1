package script;

public class Edge {
    private String _id;
    private Vertex _fromVertex;
    private Vertex _toVertex;
    private int _switchCost;

    private Edge(String id, int cost, Vertex fromVertex, Vertex toVertex){
        _id = id;
        _fromVertex = fromVertex;
        _toVertex = toVertex;
        _switchCost = cost;
    }

    public static Edge createEdge(String id,int cost, Vertex fromVertex, Vertex toVertex){
        Edge edge = new Edge(id, cost, fromVertex, toVertex);
        fromVertex.addOutgoingEdge(edge);
        return edge;
    }

    public String getId() {
        return _id;
    }

    public int getSwitchCost() {
        return _switchCost;
    }

    public Vertex getFromVertex() {
        return _fromVertex;
    }

    public Vertex getToVertex() {
        return _toVertex;
    }
}
