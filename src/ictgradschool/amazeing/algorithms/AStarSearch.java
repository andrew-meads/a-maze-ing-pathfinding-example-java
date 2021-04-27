package ictgradschool.amazeing.algorithms;

import ictgradschool.amazeing.Point;
import ictgradschool.amazeing.graph.Edge;
import ictgradschool.amazeing.graph.Graph;
import ictgradschool.amazeing.graph.Node;

import java.util.*;

public class AStarSearch extends DijkstraSearch {

    public AStarSearch(Graph graph, Node startNode, Node goalNode) {
        super(graph, startNode, goalNode);
    }

    @Override
    protected int comparePriority(Node node1, Node node2) {
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
