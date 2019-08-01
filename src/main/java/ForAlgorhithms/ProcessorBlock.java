package ForAlgorhithms;

import Graph.Vertex;

/**
 * Class to represent the time used on a processor
 */
public class ProcessorBlock {
    private Vertex v;
    private int startTime;
    private int endTime;

    @Override
    public String toString() {
        return v.getId();
    }
}
