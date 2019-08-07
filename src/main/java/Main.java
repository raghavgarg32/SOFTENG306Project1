import algorhithm.DFS;
import files.DotParser;
import algorhithm.AStar;
import graph.Graph;
import files.OutputCreator;
import scheduler.State;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
      //  System.out.println(g);

        DotParser dp = new DotParser(new File("data/input.dot"));
        Graph g1 = null;
        try {
            g1 = dp.parseGraph();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //State solution = new AStar(2,g1).runAlgorhithm();

       State solution = new AStar(2,g1).runAlgorhithm();


        System.out.println(solution);
        OutputCreator out = new OutputCreator(solution);
        out.displayOutputOnConsole();


    }

}

