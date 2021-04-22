package ictgradschool.amazeing.graph;

import ictgradschool.amazeing.Point;
import ictgradschool.amazeing.maze.Maze;
import ictgradschool.amazeing.maze.TileTypes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Graph<T> {

    private final List<Node<T>> nodes = new ArrayList<>();

    public static Graph<Point> fromMaze(Maze maze) {
        Graph<Point> g = new Graph<>();

        @SuppressWarnings("unchecked")
        Node<Point>[][] nodes = new Node[maze.getWidth()][maze.getHeight()];

        // Create nodes (don't create nodes in wall locations)
        for (int x = 0; x < maze.getWidth(); x++) {
            for (int y = 0; y < maze.getHeight(); y++) {
                TileTypes tile = maze.getTile(x, y);
                if (tile == TileTypes.Blank) {
                    nodes[x][y] = new Node<>(new Point(x, y));
                    g.nodes.add(nodes[x][y]);
                }
            }
        }

        // Create edges
        for (int x = 0; x < maze.getWidth(); x++) {
            for (int y = 0; y < maze.getHeight(); y++) {
                Node<Point> currentNode = nodes[x][y];
                if (currentNode != null) {

                    for (int otherX = Math.max(0, x - 1); otherX <= Math.min(x + 1, maze.getWidth() - 1); otherX++) {
                        for (int otherY = Math.max(0, y - 1); otherY <= Math.min(y + 1, maze.getHeight() - 1); otherY++) {
                            Node<Point> otherNode = nodes[otherX][otherY];
                            if (otherNode != null && otherNode != currentNode) {
                                currentNode.addEdgeTo(otherNode);
                            }
                        }
                    }

                }
            }
        }

        return g;
    }

    public List<Node> getNodes() {
        return Collections.unmodifiableList(nodes);
    }
}
