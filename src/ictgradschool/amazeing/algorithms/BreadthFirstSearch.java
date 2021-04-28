package ictgradschool.amazeing.algorithms;

import ictgradschool.amazeing.graph.Graph;
import ictgradschool.amazeing.graph.Node;

import java.util.*;

/**
 * The most basic search algorithm included in this project. When a node is explored, all its neighbours are added
 * to a standard queue. This frontier is explored in a FIFO (standard queue) order. Path costs and estimates are not
 * considered in this algorithm.
 *
 * @author Andrew Meads
 */
public class BreadthFirstSearch extends AbstractSearchAlgorithm {

    public BreadthFirstSearch(Graph graph, Node startNode, Node goalNode) {
        super(graph, startNode, goalNode);
    }

    /**
     * Explores a single node by removing it from the head of the frontier queue, locating its neighbours in the graph,
     * and adding those neighbours to the frontier. We will also record the path from the start node to each of those
     * neighbours by adding them to the {@link AbstractSearchAlgorithm#cameFrom} map.
     */
    @Override
    protected void doStep() {

        Node current = this.frontier.remove();

        Set<Node> neighbors = this.graph.getNeighbours(current);
        for (Node next : neighbors) {
            if (!cameFrom.containsKey(next)) {
                frontier.add(next);
                cameFrom.put(next, current);
            }
        }
    }

    /**
     * The BFS algorithm uses a standard FIFO queue.
     *
     * @return a new instance of {@link ArrayDeque}.
     */
    @Override
    protected Queue<Node> createFrontier() {
        return new ArrayDeque<>();
    }
}
