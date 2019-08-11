package algorithm;

import graph.Graph;
import scheduler.State;

import java.util.Stack;

/**
 * Algorithm which deals with using the DFS implementation. Here, a stack is used to allow
 * us to view the last assigned state and then allows us to explore the states that follow
 * the last assigned state. This enables us to implement the DFS algorithm.
 */
public class DFS implements Algorithm {
    private final int numP;
    private Stack<State> stateStack;
    Graph graph;
    private int boundValue;

    public DFS(int numProcessors, Graph g) {
        graph = g;
        stateStack = new Stack<State>();
        numP = numProcessors;

        //Init state
        stateStack.push(new State(numP, graph));
        boundValue = Integer.MAX_VALUE;



    }

    /**
     * This is the implementation of the DFS algorithm
     * @return
     */
    public State runAlgorithm() {
        State bestState = new State(numP, graph);
        while (!stateStack.empty()) {
            //get latest state
            State state = stateStack.pop();
            int currentBoundValue = boundValue;
            //If cost of state equals or greater than bound value don't visit its following states then
            if (state.getCurrentCost() < currentBoundValue) {
                if (state.allVisited()) {
                    boundValue = state.getCurrentCost();
                    bestState = state;
                } else {
                    for (State nextState : state.generatePossibilities()) {
                        stateStack.push(nextState);
                    }
                }
            }

        }
        return bestState;
    }
    /**
     * Set Initial State
     * Put it on stack
     * Pop stack to get new state
     * check states cost
     * if greater or equal to bound value
     *  don't visit its following states
     * else
     *  if state covers all vertices
     *      set the cost as bound value as long it is less than current bound value
     *      and set it as most optimal state
     *  else
     *      get all of the following possibilities of state and push to stack
     *
     * return optimal state
     *
     */


}