package ictgradschool.amazeing.gui;

import ictgradschool.amazeing.algorithms.IGraphSearchAlgorithm;
import ictgradschool.amazeing.graph.Node;
import ictgradschool.amazeing.Point;

import java.awt.*;
import java.util.List;
import java.util.Collection;

/**
 * The responsibility for drawing the search algorithm has been delegated from {@link MazePanel} to this class,
 * to reduce the amount of code in that class.
 * <p>
 * Note: This will paint the top-left tile of the maze at position (0, 0). If you wish to change this, use
 * {@link Graphics#translate(int, int)} prior to calling the methods in this class.
 *
 * @author Andrew Meads
 */
public class SearchPainter {

    // Constants. Change these to alter the look of the painted algorithm.
    private static final Color FRONTIER_COLOR = new Color(0, 0, 1, 0.5f);
    private static final Color REACHED_COLOR = new Color(0.7f, 0.4f, 0.4f, 0.5f);
    private static final Color PATH_COLOR = Color.red;

    /**
     * Paints the given algorithm. This method will paint the algorithm's reached (explored) nodes, frontier, and, if
     * a path has been found, will also paint the path.
     *
     * @param algorithm the algorithm to paint.
     * @param g         the graphics object to use to paint the algorithm.
     */
    public static void paintAlgorithm(IGraphSearchAlgorithm algorithm, Graphics g) {
        paintNodes(algorithm.getReached(), REACHED_COLOR, g);
        paintNodes(algorithm.getFrontier(), FRONTIER_COLOR, g);

        if (algorithm.isPathFound()) {
            paintPath(algorithm.getPath(), (Graphics2D) g);
        }
    }

    /**
     * Paints the given graph nodes in the given color.
     *
     * @param nodes the nodes to paint.
     * @param color the color to paint the nodes.
     * @param g     the graphics object to use to paint the nodes.
     */
    private static void paintNodes(Collection<Node> nodes, Color color, Graphics g) {
        g.setColor(color);
        for (Node node : nodes) {
            Point p = (Point) node.getData();
            g.fillRect(p.getX() * MazePainter.TILE_WIDTH, p.getY() * MazePainter.TILE_HEIGHT, MazePainter.TILE_WIDTH, MazePainter.TILE_HEIGHT);
        }
    }

    /**
     * Paints a polyline of n points, n being the size of the given path. Each point in the polyline will be centered
     * on the corresponding node.
     *
     * @param path the nodes in the path to paint.
     * @param g    the graphics object to use to paint the path. Note: This needs to be cast to {@link Graphics2D} since
     *             we are using the {@link Graphics2D#setStroke(Stroke)} method, which isn't available in the standard
     *             {@link Graphics} interface.
     */
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
