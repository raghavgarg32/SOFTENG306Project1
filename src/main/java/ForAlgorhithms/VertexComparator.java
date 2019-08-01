package ForAlgorhithms;

import Graph.Vertex;

import java.util.Comparator;

public class VertexComparator implements Comparator<Vertex> {
    @Override
    public int compare(Vertex vertex, Vertex t1) {
        return vertex.getLevel() - t1.getLevel();
    }
}
