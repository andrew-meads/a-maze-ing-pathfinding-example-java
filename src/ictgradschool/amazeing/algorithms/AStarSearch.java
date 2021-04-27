package ictgradschool.amazeing.algorithms;

import ictgradschool.amazeing.Point;
import ictgradschool.amazeing.graph.Edge;
import ictgradschool.amazeing.graph.Graph;
import ictgradschool.amazeing.graph.Node;

import java.util.*;

public class AStarSearch extends AbstractSearchAlgorithm {

    private Map<Node, Double> costSoFar;

    public AStarSearch(Graph graph, Node startNode, Node goalNode) {
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
        priority1 += heuristic(node1);
        priority2 += heuristic(node2);
        return Double.compare(priority1, priority2);
    }

    private double heuristic(Node node) {
        Point point1 = (Point) node.getData();
        Point point2 = (Point) goalNode.getData();
        double h = Math.pow(point2.getX() - point1.getX(), 2) + Math.pow(point2.getY() - point1.getY(), 2);
        return Math.sqrt(h);
    }
}
