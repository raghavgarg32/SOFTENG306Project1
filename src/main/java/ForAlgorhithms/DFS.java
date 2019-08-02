package ForAlgorhithms;

import Graph.Graph;

import java.util.List;

public class DFS {
    private State initState;
    private List<Processor> procList;
    private Processor proc1;
    private Processor proc2;

    public void runDFS(Graph g1){

        System.out.println(g1);
        System.out.println(g1.getVertex("a"));
        System.out.println(g1.getVertex("a").getCost());
        proc1 = new Processor();
        proc2 = new Processor();

        procList.add(proc1);
        procList.add(proc2);




    }

    //create processors

    /**
     * Create processors
     * empty in beginning
     * add init value
     */

    private void createInitState() {

    }
}
