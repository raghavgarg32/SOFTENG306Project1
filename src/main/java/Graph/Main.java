package Graph;

public class Main {

    public static void main(String[] args) {
        GraphCreator graphCreator = new GraphCreator("data/input.dot");
        Graph g = graphCreator.createGraph();
        System.out.println(g);
    }


}

