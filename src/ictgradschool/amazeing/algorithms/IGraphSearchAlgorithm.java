package ictgradschool.amazeing.algorithms;

import ictgradschool.amazeing.graph.Node;

import java.util.Collection;
import java.util.List;

public interface IGraphSearchAlgorithm {

    void addAlgorithmListener(IAlgorithmListener l);

    void removeAlgorithmListener(IAlgorithmListener l);

    boolean isDone();

    boolean isPathFound();

    void reset();

    void run();

    boolean step();

    Collection<Node> getReached();

    Collection<Node> getFrontier();

    List<Node> getPath();

}
