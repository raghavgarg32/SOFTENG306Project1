package Graph;

import Files.DotParser;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        GraphCreator graphCreator = new GraphCreator("data/input.dot");
        Graph g = graphCreator.createGraph();


        DotParser dp = new DotParser(new File("data/input.dot"));
        Graph g1 = null;
        try {
            g1 = dp.parseGraph();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        g.getVertex("a").calculateBottomLevel();
    }


}

