package ForAlgorhithms;

import Graph.Vertex;

import java.util.List;

public class Processor {
    List<Vertex> vertexList;
    Vertex lastVertex;
    int lastCost;

    public int getProcessorNumber() {
        return processorNumber;
    }

    public void setProcessorNumber(int processorNumber) {
        this.processorNumber = processorNumber;
    }

    int processorNumber;



    public List<Vertex> getVertexList() {
        return vertexList;
    }

    public void setVertexList(List<Vertex> vertexList) {
        this.vertexList = vertexList;
    }

    public Vertex getLastVertex() {
        return lastVertex;
    }

    public void setLastVertex(Vertex lastVertex) {
        this.lastVertex = lastVertex;
    }

    public int getLastCost() {
        return lastCost;
    }

    public void setLastCost(int lastCost) {
        this.lastCost = lastCost;
    }
}
