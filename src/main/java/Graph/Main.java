package Graph;

import Files.DotParser;
import ForAlgorhithms.DFS;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        GraphCreator graphCreator = new GraphCreator("data/input.dot");
        Graph g = graphCreator.createGraph();
        System.out.println(g);

        DotParser dp = new DotParser(new File("data/input.dot"));
        Graph g1 = null;
        try {
            g1 = dp.parseGraph();
            DFS dfs = new DFS();
            dfs.runDFS(g1);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}

