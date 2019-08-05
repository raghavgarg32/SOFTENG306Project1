package files;

import graph.Edge;
import graph.Graph;
import graph.Vertex;
import scheduler.State;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * This class is used for creating the output file in the same structure outlined within the brief
 */
public class OutputCreator {

    private State state;
    private Graph graph;

    public OutputCreator(State state){
        this.state = state;
        state.outputFormat();
        graph = state.getG();
    }

    /**
     * Displays output string on the console
     */
    public void displayOutputOnConsole() {
        System.out.println(constructOutputLine());
    }

    /**
     * Creates a file within the /data/ directory for output
     * @param name
     */
    public void createOutputFile(String name) {
        String filePath = "data/" + name + ".dot";
        String output = constructOutputLine();
        try (PrintWriter out = new PrintWriter(filePath)) {
            out.println(output);
        } catch (FileNotFoundException fnfe){
            fnfe.printStackTrace();
        }

    }

    /**
     * Constructs a string for the output with the same structure as that which is in the brief
     * @return String which represents the constructed output
     */
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
