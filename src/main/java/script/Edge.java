package script;

public class Edge {
    private String id;
    private Vertex fromVertex;
    private Vertex toVertex;
    private int switchCost;

     Edge(String id, int cost, Vertex fromVertex, Vertex toVertex){
        this.id = id;
        this.fromVertex = fromVertex;
        this.toVertex = toVertex;
        switchCost = cost;
         fromVertex.addOutgoingEdge(this);
    }


    public String getId() {
        return id;
    }

    public int getSwitchCost() {
        return switchCost;
    }

    public Vertex getFromVertex() {
        return fromVertex;
    }

    public Vertex getToVertex() {
        return toVertex;
    }
}
