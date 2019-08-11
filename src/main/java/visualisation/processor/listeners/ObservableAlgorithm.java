package visualisation.processor.listeners;

import algorithm.AlgorithmEvents;

public interface ObservableAlgorithm {
    void addListener(SchedulerListener listener);
    void removeListener(SchedulerListener listener);
    void fireEvent(AlgorithmEvents event);

}
