package ictgradschool.amazeing.algorithms;

import ictgradschool.amazeing.graph.Edge;
import ictgradschool.amazeing.graph.Graph;
import ictgradschool.amazeing.graph.Node;

import java.util.*;

public class DijkstraSearch extends AbstractSearchAlgorithm {

    private Map<Node, Double> costSoFar;

    public DijkstraSearch(Graph graph, Node startNode, Node goalNode) {
        super(graph, startNode, goalNode);
    }

    @Override
    public void reset() {
        super.reset();

        this.costSoFar = new HashMap<>();
        this.costSoFar.put(startNode, 0.0);

        fireProgressUpdate();
    }

    @Override
    public boolean step() {
        if (isDone()) {
            return true;
        }

        Node current = this.frontier.remove();

        List<Edge> edges = this.graph.getEdgesFrom(current);
        for (Edge e : edges) {

            Node next = e.getTo();
            double newCost = this.costSoFar.get(current) + e.getCost();

            if (!this.costSoFar.containsKey(next) || newCost < this.costSoFar.get(next)) {
                this.costSoFar.put(next, newCost);
                this.frontier.add(next);
                this.cameFrom.put(next, current);
            }
        }

        fireProgressUpdate();
        return isDone();
    }

    @Override
    protected Queue<Node> createFrontier() {
        return new PriorityQueue<>(this::comparePriority);
    }

    private int comparePriority(Node node1, Node node2) {
        double priority1 = this.costSoFar.containsKey(node1) ? this.costSoFar.get(node1) : 0;
        double priority2 = this.costSoFar.containsKey(node2) ? this.costSoFar.get(node2) : 0;
        return Double.compare(priority1, priority2);
    }
}
