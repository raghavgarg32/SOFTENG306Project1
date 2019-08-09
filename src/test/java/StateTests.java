import algorhithm.AStar;
import algorhithm.DFS;
import files.DotParser;
import graph.Graph;
import org.junit.Assert;
import org.junit.Test;
import scheduler.State;

import java.io.File;
import java.io.FileNotFoundException;

public class StateTests {
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
     * This tests if the calculation of the bottom level is correct with the default graph
     */
    @Test
    public void testDoubleProcessor() {
        testNodes7double();
        testNodes8double();
        testNodes9double();
        testNodes10double();
        testNodes11double();
    }

    @Test
    public void testNodes8double(){
        Graph createdGraph = createGraph("Nodes_8_Random.dot");
        State optimalState = new DFS(2,createdGraph).runAlgorhithm();
        Assert.assertEquals(optimalState.getCurrentCost(),581);
    }
    @Test
    public void testNodes9double(){
        Graph createdGraph = createGraph("Nodes_9_SeriesParallel.dot");
        State optimalState = new AStar(2,createdGraph).runAlgorhithm();
        Assert.assertEquals(optimalState.getCurrentCost(),55);
    }
    @Test
    public void testNodes10double(){
        Graph createdGraph = createGraph("Nodes_10_Random.dot");
        State optimalState = new DFS(2,createdGraph).runAlgorhithm();
        Assert.assertEquals(optimalState.getCurrentCost(),50);
    }
    @Test
    public void testNodes11double(){
        Graph createdGraph = createGraph("Nodes_11_OutTree.dot");
        State optimalState = new DFS(2,createdGraph).runAlgorhithm();
        Assert.assertEquals(optimalState.getCurrentCost(),350);
    }
    @Test
    public void testNodes7double(){
        Graph createdGraph = createGraph("Nodes_7_OutTree.dot");
        State optimalState = new DFS(2,createdGraph).runAlgorhithm();
        Assert.assertEquals(optimalState.getCurrentCost(),28);
    }/**
     * This tests if the calculation of the bottom level is correct with the default graph
     */
    @Test
    public void testSingleProcessor() {
        testNodes7();
        testNodes8();
        testNodes9();
        testNodes10();
        testNodes11();
    }

    @Test
    public void testNodes11(){
        Graph createdGraph = createGraph("Nodes_11_OutTree.dot");
        State optimalState = new DFS(1,createdGraph).runAlgorhithm();
        Assert.assertEquals(optimalState.getCurrentCost(),640);
    }
    @Test
    public void testNodes8(){
        Graph createdGraph = createGraph("Nodes_8_Random.dot");
        State optimalState = new DFS(1,createdGraph).runAlgorhithm();
        Assert.assertEquals(optimalState.getCurrentCost(),969);
    }
    @Test
    public void testNodes10(){
        Graph createdGraph = createGraph("Nodes_10_Random.dot");
        State optimalState = new DFS(1,createdGraph).runAlgorhithm();
        Assert.assertEquals(optimalState.getCurrentCost(),63);
    }
    @Test
    public void testNodes9(){
        Graph createdGraph = createGraph("Nodes_9_SeriesParallel.dot");
        State optimalState = new DFS(1,createdGraph).runAlgorhithm();
        Assert.assertEquals(optimalState.getCurrentCost(),55);
    }
    @Test
    public void testNodes7(){
        Graph createdGraph = createGraph("Nodes_7_OutTree.dot");
        State optimalState = new DFS(1,createdGraph).runAlgorhithm();
        Assert.assertEquals(optimalState.getCurrentCost(),40);
    }
}
