import Files.DotParser;
import Graph.Graph;
import Graph.GraphCreator;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

public class GraphTests {

    /**
     * This method is helps test different input files.
     * @param inputURL
     */
    private Graph createGraph(String inputURL) {
        GraphCreator graphCreator = new GraphCreator("data/" + inputURL);
        return graphCreator.createGraph();
    }


    /**
     * This tests if the user created graph is the same as the library created one.
     * TODO: Probably will remove this in the future.
     */
    @Test
    public void testGraphCreation() {
        Graph createdGraph = createGraph("input.dot");
        DotParser dp = new DotParser(new File("data/input.dot"));
        Graph calculatedGraph = null;
        try {
            calculatedGraph = dp.parseGraph();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(createdGraph.toString(),calculatedGraph.toString());
    }

    /**
     * This tests if the calculation of the bottom level is correct with the default graph
     */
    @Test
    public void testCalculationOfBottomLevel() {
        Graph createdGraph = createGraph("input.dot");
        Assert.assertEquals(7,createdGraph.calculateBottomLevel());
    }

    /**
     * This tests if the calculation of the bottom level is correct for a large graph
     */
    @Test
    public void testCalculationOfBottomLevelForSecondary() {
        Graph createdGraph = createGraph("input2.dot");
        Assert.assertEquals(14,createdGraph.calculateBottomLevel());
    }

    /**
     * This tests if the calculation of the bottom level is correct for a linear graph
     */
    @Test
    public void testCalculationOfBottomLevelForLinearGraph() {
        Graph createdGraph = createGraph("input3.dot");
        Assert.assertEquals(10,createdGraph.calculateBottomLevel());
    }
}
