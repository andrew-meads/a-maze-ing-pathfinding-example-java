package ictgradschool.amazeing.gui;

import ictgradschool.amazeing.algorithms.IGraphSearchAlgorithm;
import ictgradschool.amazeing.graph.Node;
import ictgradschool.amazeing.Point;

import java.awt.*;
import java.util.Collection;

public class SearchPainter {

    private static SearchPainter instance;

    private static final Color FRONTIER_COLOR = new Color(0, 0, 1, 0.5f);
    private static final Color REACHED_COLOR = new Color(0.7f, 0.4f, 0.4f, 0.5f);

    public static SearchPainter getInstance() {
        if (instance == null) {
            instance = new SearchPainter();
        }
        return instance;
    }

    private SearchPainter() {

    }

    public void paintAlgorithm(IGraphSearchAlgorithm algorithm, Graphics g) {
        paintNodes(algorithm.getReached(), REACHED_COLOR, g);
        paintNodes(algorithm.getFrontier(), FRONTIER_COLOR, g);
    }

    private void paintNodes(Collection<Node> nodes, Color color, Graphics g) {
        g.setColor(color);
        for (Node node : nodes) {
            Point p = (Point) node.getData();
            g.fillRect(p.getX() * MazePainter.TILE_WIDTH, p.getY() * MazePainter.TILE_HEIGHT, MazePainter.TILE_WIDTH, MazePainter.TILE_HEIGHT);
        }
    }
}
