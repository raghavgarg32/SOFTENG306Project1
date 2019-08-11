package algorithm;

import javafx.event.EventType;
import scheduler.State;
import visualisation.processor.listeners.ObservableAlgorithm;
import visualisation.processor.listeners.SchedulerListener;

import java.util.ArrayList;
import java.util.List;


public abstract class AlgorithmHandler implements ObservableAlgorithm {
    private List<SchedulerListener> listeners = new ArrayList<>();
    private State state;
    private AlgorithmEvents eventType;
    @Override
    public void addListener(SchedulerListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(SchedulerListener listener) {
        listeners.remove(listener);
    }

    @Override
    public void fireEvent(AlgorithmEvents event) {
        this.eventType = event;
        fire();
    }

    public void fireEvent(AlgorithmEvents event, State state) {
        this.eventType = event;
        this.state = state;
        fire();

    }
    private void fire() {
        switch(eventType) {
            case ALGORITHM_FINISHED:
                for (SchedulerListener listener : listeners) {
                    listener.setState(state);
                }
                return;
        }
    }
}
