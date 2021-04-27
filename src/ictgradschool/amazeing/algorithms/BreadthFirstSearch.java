package ictgradschool.amazeing.algorithms;

import ictgradschool.amazeing.graph.Graph;
import ictgradschool.amazeing.graph.Node;

import java.util.*;

public class BreadthFirstSearch extends AbstractSearchAlgorithm {

    public BreadthFirstSearch(Graph graph, Node startNode, Node goalNode) {
        super(graph, startNode, goalNode);
        reset();
    }

    @Override
    public boolean step() {
        if (isDone()) {
            return true;
        }

        Node current = this.frontier.remove();

        List<Node> neighbors = this.graph.getNeighbours(current);
        for (Node next : neighbors) {
            if (!cameFrom.containsKey(next)) {
                frontier.add(next);
                cameFrom.put(next, current);
            }
        }

        fireProgressUpdate();
        return isDone();
    }

    @Override
    protected Queue<Node> createFrontier() {
        return new ArrayDeque<>();
    }
}
