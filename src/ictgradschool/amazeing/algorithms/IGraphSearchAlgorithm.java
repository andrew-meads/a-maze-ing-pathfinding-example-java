package ictgradschool.amazeing.algorithms;

import ictgradschool.amazeing.Point;
import ictgradschool.amazeing.graph.Node;

import java.util.List;

public interface IGraphSearchAlgorithm {

    boolean isDone();

    boolean isPathFound();

    void reset();

    void run();

    boolean step();

    List<Node<Point>> getPath();

}
