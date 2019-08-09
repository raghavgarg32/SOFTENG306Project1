package graph;

import graph.Vertex;

import java.util.Comparator;

/**
 * Comparator to help find which vertex is greater level wise.
 */
public class VertexComparator implements Comparator<Vertex> {
    @Override
    public int compare(Vertex vertex, Vertex t1) {
        return vertex.getLevel() - t1.getLevel();
    }
}
