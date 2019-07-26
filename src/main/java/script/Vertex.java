package script;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    private int _cost;
    private List<Edge> _outgoingEdges;

    public Vertex(int cost){
        _cost = cost;
        _outgoingEdges = new ArrayList<>();
    }

    public void addOutgoingEdge(Edge edge){
        _outgoingEdges.add(edge);
    }

    public int getCost() {
        return _cost;
    }

    public List<Edge> getOutgoingEdges() {
        return _outgoingEdges;
    }
}
