package ForAlgorhithms;

import Graph.Vertex;

public class ProcessorBlock {
    private Vertex v;
    private int startTime;
    private int endTime;

    @Override
    public String toString() {
        return v.getId();
    }
}
