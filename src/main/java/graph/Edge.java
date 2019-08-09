package graph;

/**
 * A class to represent an edge of our graphs
 */
public class Edge {
    private String id;
    private Vertex fromVertex;
    private Vertex toVertex;
    private int switchCost;


    public Edge(Vertex fromVertex, Vertex toVertex, int switchCost) {
        this.fromVertex = fromVertex;
        this.toVertex = toVertex;
        this.switchCost = switchCost;
        id = fromVertex.getId() + "->" + toVertex.getId();
        fromVertex.addOutgoingEdge(this);
        toVertex.addIncomingEdge(this);

    }

    Edge(String id, int cost, Vertex fromVertex, Vertex toVertex){
        this.id = id;
        this.fromVertex = fromVertex;
        this.toVertex = toVertex;
        switchCost = cost;
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

    @Override
    public String toString() {
        String weight = Integer.toString(switchCost);
        String output = id +  "\t[Weight=" + weight + "];";
        return output;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public boolean equals(Object o){
        Edge e = (Edge) o;
        return this.toString().equals(e.toString());
    }
}
