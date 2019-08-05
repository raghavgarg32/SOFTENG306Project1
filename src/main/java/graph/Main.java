package graph;

import files.DotParser;
import algorhithm.AStar;

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
        System.out.println( new AStar(2,g1).runAlgorhithm());
       // System.out.println(g1);
        //System.out.println(g1.calculateBottomLevel());

    }



}

