package algorithm;

import graph.Graph;
import visualisation.AlgorithmDataStorage;
import visualisation.AlgorithmListener;
import visualisation.processor.listeners.SchedulerListener;

public class AlgorithmFactory  {
    public Algorithm createAlgorithm(AlgorithmChoice choice, String[] args, Graph graph) throws Exception {
        SchedulerListener listener = createListener(args);
        Algorithm algorithm;
        int numberOfProcessors = Integer.parseInt(args[1]);
        switch (choice) {
            case DFS:
                algorithm = new DFS(numberOfProcessors,graph);
                break;
            case ASTAR:
                algorithm = new AStar(numberOfProcessors,graph);
                break;
            default:
                throw new Exception("Not a valid algorithm.");
        }
        algorithm.addListener(listener);
        AlgorithmDataStorage.getInstance().setListener(listener);
        return algorithm;
    }

    private SchedulerListener createListener(String[] result) {
        SchedulerListener listener = new AlgorithmListener();
        listener.setFileName(result[0]);
        listener.setNumberOfProcessors(Integer.parseInt(result[1]));
        return listener;
    }
}
