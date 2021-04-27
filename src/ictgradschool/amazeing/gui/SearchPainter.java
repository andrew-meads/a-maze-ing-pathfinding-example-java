package ictgradschool.amazeing.gui;

import ictgradschool.amazeing.algorithms.IGraphSearchAlgorithm;
import ictgradschool.amazeing.graph.Node;
import ictgradschool.amazeing.Point;

import java.awt.*;
import java.util.List;
import java.util.Collection;

public class SearchPainter {

    private static SearchPainter instance;

    private static final Color FRONTIER_COLOR = new Color(0, 0, 1, 0.5f);
    private static final Color REACHED_COLOR = new Color(0.7f, 0.4f, 0.4f, 0.5f);
    private static final Color PATH_COLOR = Color.red;

    public static void paintAlgorithm(IGraphSearchAlgorithm algorithm, Graphics g) {
        paintNodes(algorithm.getReached(), REACHED_COLOR, g);
        paintNodes(algorithm.getFrontier(), FRONTIER_COLOR, g);

        if (algorithm.isPathFound()) {
            paintPath(algorithm.getPath(), (Graphics2D) g);
        }
    }

    private static void paintNodes(Collection<Node> nodes, Color color, Graphics g) {
        g.setColor(color);
        for (Node node : nodes) {
            Point p = (Point) node.getData();
            g.fillRect(p.getX() * MazePainter.TILE_WIDTH, p.getY() * MazePainter.TILE_HEIGHT, MazePainter.TILE_WIDTH, MazePainter.TILE_HEIGHT);
        }
    }

    private static void paintPath(List<Node> path, Graphics2D g) {

        int[] xs = new int[path.size()];
        int[] ys = new int[path.size()];

        for (int i = 0; i < path.size(); i++) {
            Point p = (Point) path.get(i).getData();

            xs[i] = p.getX() * MazePainter.TILE_WIDTH + MazePainter.TILE_WIDTH / 2;
            ys[i] = p.getY() * MazePainter.TILE_HEIGHT + MazePainter.TILE_HEIGHT / 2;
        }

        g.setColor(PATH_COLOR);
        g.setStroke(new BasicStroke(3));
        g.drawPolyline(xs, ys, path.size());
    }
}
