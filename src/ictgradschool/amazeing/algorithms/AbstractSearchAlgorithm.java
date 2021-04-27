package ictgradschool.amazeing.algorithms;

import ictgradschool.amazeing.graph.Graph;
import ictgradschool.amazeing.graph.Node;

import java.util.*;

public abstract class AbstractSearchAlgorithm implements IGraphSearchAlgorithm {

    private List<IAlgorithmListener> listeners = new ArrayList<>();

    protected final Graph graph;
    protected final Node startNode, goalNode;
    protected Map<Node, Node> cameFrom;
    protected Queue<Node> frontier;

    protected AbstractSearchAlgorithm(Graph graph, Node startNode, Node goalNode) {
        this.graph = graph;
        this.startNode = startNode;
        this.goalNode = goalNode;

        reset();
    }

    @Override
    public void reset() {
        this.frontier = createFrontier();
        this.frontier.add(startNode);
        this.cameFrom = new HashMap<>();
        this.cameFrom.put(startNode, null);

        fireProgressUpdate();
    }

    @Override
    public void run() {
        while (!isDone()) {
            step();
        }
    }

    @Override
    public boolean isDone() {
        return this.frontier.isEmpty() || isPathFound();
    }

    @Override
    public boolean isPathFound() {
        return this.cameFrom.containsKey(goalNode);
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

    protected final void fireProgressUpdate() {
        for (IAlgorithmListener l : listeners) {
            l.progressUpdate(this);
        }
    }

    protected abstract Queue<Node> createFrontier();

    @Override
    public void addAlgorithmListener(IAlgorithmListener l) {
        this.listeners.add(l);
    }

    @Override
    public void removeAlgorithmListener(IAlgorithmListener l) {
        this.listeners.remove(l);
    }
}
