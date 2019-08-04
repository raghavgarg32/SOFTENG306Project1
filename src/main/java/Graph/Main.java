package Graph;

import Files.DotParser;
import ForAlgorhithms.AStar;
import ForAlgorhithms.DFS;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        GraphCreator graphCreator = new GraphCreator("data/input.dot");
        Graph g = graphCreator.createGraph();
      //  System.out.println(g);

        DotParser dp = new DotParser(new File("data/input.dot"));
        Graph g1 = null;
        try {
            g1 = dp.parseGraph();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //new AStar(2,g1).runAlgorhithm();
        new DFS(2,g1).runDFS();

        // System.out.println(g1);
        //System.out.println(g1.calculateBottomLevel());

    }



}

