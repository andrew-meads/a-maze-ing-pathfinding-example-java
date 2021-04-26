package ictgradschool.amazeing.algorithms;

import ictgradschool.amazeing.graph.Graph;
import ictgradschool.amazeing.graph.Node;

import java.util.*;

public class BreadthFirstSearch extends AbstractSearchAlgorithm {

    private final Graph graph;
    private final Node startNode, goalNode;
    private final Map<Node, Node> cameFrom = new HashMap<>();
    private final Queue<Node> frontier = new ArrayDeque<>();

    public BreadthFirstSearch(Graph graph, Node startNode, Node goalNode) {
        this.graph = graph;
        this.startNode = startNode;
        this.goalNode = goalNode;

        reset();
    }

    @Override
    public boolean isDone() {
        return frontier.isEmpty() || isPathFound();
    }

    @Override
    public boolean isPathFound() {
        return this.cameFrom.containsKey(goalNode);
    }

    @Override
    public void reset() {
        this.cameFrom.clear();
        this.cameFrom.put(startNode, null);
        this.frontier.clear();
        this.frontier.add(startNode);

        fireProgressUpdate();
    }

    @Override
    public void run() {
        while (!isDone()) {
            step();
        }
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
    public Collection<Node> getReached() {
        return Collections.unmodifiableCollection(cameFrom.keySet());
    }

    @Override
    public Collection<Node> getFrontier() {
        return Collections.unmodifiableCollection(frontier);
    }

    @Override
    public List<Node> getPath() {
        List<Node> path = new ArrayList<>();

        if (isPathFound()) {
            Node current = goalNode;
            while (current != null) {
                path.add(current);
                current = cameFrom.get(current);
            }
            Collections.reverse(path);
        }

        return path;
    }
}
