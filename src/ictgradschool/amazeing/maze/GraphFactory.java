package ictgradschool.amazeing.maze;

import ictgradschool.amazeing.Point;
import ictgradschool.amazeing.graph.Graph;
import ictgradschool.amazeing.graph.Node;

public class GraphFactory {

    public static Graph fromMaze(Maze maze) {
        Graph g = new Graph();

        Node[][] nodes = new Node[maze.getWidth()][maze.getHeight()];

        // Create nodes (don't create nodes in wall locations)
        for (int x = 0; x < maze.getWidth(); x++) {
            for (int y = 0; y < maze.getHeight(); y++) {
                TileTypes tile = maze.getTile(new Point(x, y));
                if (tile == TileTypes.Blank) {
                    nodes[x][y] = new Node(new Point(x, y));
                    g.addNode(nodes[x][y]);
                }
            }
        }

        // Create edges
        for (int x = 0; x < maze.getWidth(); x++) {
            for (int y = 0; y < maze.getHeight(); y++) {
                Node currentNode = nodes[x][y];
                if (currentNode != null) {

                    for (int otherX = Math.max(0, x - 1); otherX <= Math.min(x + 1, maze.getWidth() - 1); otherX++) {
                        for (int otherY = Math.max(0, y - 1); otherY <= Math.min(y + 1, maze.getHeight() - 1); otherY++) {
                            Node otherNode = nodes[otherX][otherY];
                            if (otherNode != null && otherNode != currentNode) {

                                g.addEdge(currentNode, otherNode);
                            }
                        }
                    }

                }
            }
        }

        return g;
    }

}
