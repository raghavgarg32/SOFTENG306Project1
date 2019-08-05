package files;

import graph.Edge;
import graph.Graph;
import graph.Vertex;
import scheduler.State;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class OutputCreator {

    private State state;
    private Graph graph;

    public OutputCreator(State state){
        this.state = state;
        state.outputFormat();
        graph = state.getG();
    }

    public void displayOutputOnConsole() {
        System.out.println(constructOutputLine());
    }

    public void createOutputFile(String fileLocation) {

    }

    private String constructOutputLine() {
        HashMap<String, Vertex> vertices = graph.getVertexHashMap();
        HashMap<String, Edge> edges = graph.getEdgeHashMap();
        Iterator it = vertices.entrySet().iterator();

        StringBuilder output = new StringBuilder("digraph " + graph.getName() + " {");

        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            Vertex v = (Vertex) pair.getValue();
            output.append("\n\t").append(v.toStringSolution());
            it.remove();
        }

        Iterator it2 = edges.entrySet().iterator();
        while (it2.hasNext()) {
            Map.Entry pair2 = (Map.Entry)it2.next();
            Edge e = (Edge) pair2.getValue();
            output.append("\n\t").append(e.toString());
            it2.remove();
        }

        output.append("\n}");

        return output.toString();
    }

}
