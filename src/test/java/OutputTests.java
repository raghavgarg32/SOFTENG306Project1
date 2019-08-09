import files.DotParser;
import files.OutputCreator;
import graph.Graph;
import jdk.javadoc.internal.doclets.toolkit.util.DocFinder;
import org.junit.Test;
import scheduler.State;

import java.io.File;
import java.io.FileNotFoundException;

public class OutputTests {


    /**
     * This method is helps test different input files.
     * @param inputURL
     */
    private Graph createGraph(String inputURL) {

        DotParser dp = new DotParser(new File("data/" + inputURL));
        Graph g1 = null;
        try {
            g1 = dp.parseGraph();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return g1;
    }

    /**
     * Tests if the output file is correct.
     * This one is tricky to test as there are multiple correct answers.
     * Assume the output will be the same as what the brief says.
     */
    @Test
    public void testOutputFile() {
        Graph g = createGraph("input3.dot");
        State s = new State(1, g);
        OutputCreator oc = new OutputCreator(s);
        oc.createOutputFile("output3.dot");

        //Graph g1 = createGraph("output3.dot");
        //System.out.println(g1);
    }
}
