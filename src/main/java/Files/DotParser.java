package Files;

import Graph.Graph;
import com.paypal.digraph.parser.GraphEdge;
import com.paypal.digraph.parser.GraphNode;
import com.paypal.digraph.parser.GraphParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import Graph.Vertex;
import Graph.Edge;
import Graph.Graph;

public class DotParser {
    File f;

    public DotParser(File f) {
        this.f = f;
    }

    public Graph parseGraph() throws FileNotFoundException {
        GraphParser graphParser = new GraphParser(new FileInputStream(f));
        Graph g = new Graph(graphParser.getGraphId());

        for (GraphNode node : graphParser.getNodes().values()) {
            String nodeName = node.getId();
            String weight = node.getAttribute("Weight").toString();
            Integer weightInt = Integer.valueOf(weight);
            Vertex toAdd = new Vertex(nodeName, weightInt);
            g.addVertex(nodeName, toAdd);
        }

        for (GraphEdge e : graphParser.getEdges().values()) {
            String fromVertex = e.getNode1().getId();
            String toVertex = e.getNode2().getId();
            String weight = e.getAttribute("Weight").toString();
            Integer weightInt = Integer.valueOf(weight);
            Vertex from = g.getVertex(fromVertex);
            Vertex to = g.getVertex(toVertex);

            Edge toAdd = new Edge(from, to, weightInt);
            from.addOutgoingEdge(toAdd);

        }

        return g;

    }
}
