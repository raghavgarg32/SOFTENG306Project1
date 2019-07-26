package Graph;

import java.io.*;

public class GraphCreator {
    private File f;
    private Graph g;
    private String str;

    public GraphCreator(String file) {
        File f = new File(file);
        this.f = f;
    }

    public Graph createGraph() {
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            StringParser stringParser = new StringParser();
            while ((str = br.readLine()) != null) {
                stringParser.setStr(str);
                stringParser.processString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return g;
    }

    private class StringParser {
        private String str;

        public void setStr(String str) {
            this.str = str;
        }

        private boolean firstLine() {
            return str.contains("{");
        }

        private boolean isEdge() {
            return str.contains("->");
        }

        private boolean isVertex() {
            return str.contains("Weight");
        }

        /**
         * Formats the input file into a graph object form, and populates the graph
         */
        private void processString() {
            if (firstLine()) {
                g = new Graph(getGraphName());
            }
            else if (isEdge()) {
                processEdge();
            }
            else if (isVertex()) {
                processVertex();
            }
        }

        /**
         * reads the first line of the text input for the name of the graph
         *
         * @return
         */
        private String getGraphName() {
            return str.substring(str.indexOf("\"") + 1, str.lastIndexOf("\""));
        }

        /**
         * formats the strings that represent vertices into vertex objects
         *
         * @return
         */
        private Vertex processVertex() {
            String[] values = str.split("\\t");
            String name = values[0];
            int weight = getWeight(values[1]);
            Vertex v = new Vertex(name, weight);
            g.addVertex(name, v);
            return v;
        }

        /**
         * formats the strings that represent edges into edge objects
         *
         * @return
         */
        private Edge processEdge() {
            String[] values = str.split("\\t");
            String name = values[0];
            int weight = getWeight(values[1]);

            String[] vertices = values[0].split("->");
            Vertex fromVertex = g.getVertex(vertices[0]);
            Vertex toVertex = g.getVertex(vertices[1]);

            Edge e = new Edge(name, weight, fromVertex, toVertex);
            fromVertex.addOutgoingEdge(e);
            g.addEdge(name, e);

            return e;
        }

        /**
         * Only takes in strings of format "[Weight=x]" where x is an integer, and returns the integer
         *
         * @return integer value of x
         */
        private int getWeight(String str) {
            str = str.replaceAll("\\D+", "");
            int weight = Integer.parseInt(str);
            return weight;
        }
    }
}
