package Graph;

import Files.DotParser;
import ForAlgorhithms.AStar;
import ForAlgorhithms.DFS;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {
        String [] files = new String[] {"data/Nodes_9_SeriesParallel.dot","data/Nodes_10_Random.dot","data/Nodes_11_OutTree.dot"};
        for (int i = 0; i < files.length; i++) {

            DotParser dp = new DotParser(new File(files[i]));
            Graph g1 = null;
            try {
                g1 = dp.parseGraph();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            new AStar(2,g1).runAlgorhithm();
            //new DFS(2,g1).runDFS();
            System.out.println(new DFS(1, g1).runDFS());
        }


    }



}

