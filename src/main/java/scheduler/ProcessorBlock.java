package scheduler;

import graph.Vertex;

import java.util.Objects;

/**
 * Class to represent the time used on a processor
 */
public class ProcessorBlock implements Comparable<ProcessorBlock>{
    private Vertex v;
    private int startTime;
    private int endTime;

    public ProcessorBlock(Vertex v, int startTime) {
        this.v = v;
        this.startTime = startTime;
        this.endTime = v.getCost() + startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public Vertex getV() {
        return v;
    }

    public int getStartTime() {
        return startTime;
    }

    @Override
    public String toString() {
        return "id:" + v.getId() +" start_time:" + startTime + " end_time:" + endTime;
    }

    @Override
    public int compareTo(ProcessorBlock o) {
       int result = -startTime +o.startTime;
       if(result == 0){
           result =  -endTime + o.endTime;
       }
       return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessorBlock that = (ProcessorBlock) o;
        return startTime == that.startTime &&
                Objects.equals(v, that.v);
    }

    @Override
    public int hashCode() {
        return Objects.hash(v, startTime);
    }
}
