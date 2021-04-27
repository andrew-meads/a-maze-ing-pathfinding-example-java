package ictgradschool.amazeing.maze;

import ictgradschool.amazeing.Point;
import ictgradschool.amazeing.graph.Graph;
import ictgradschool.amazeing.graph.Node;

public class GraphFactory {

    public static Graph fromMaze(Maze maze) {
        return fromMaze(maze, false);
    }

    public static Graph fromMaze(Maze maze, boolean allowDiagonalMovement) {
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
                Point p = new Point(x, y);

                addStraightEdges(g, p, nodes);

                if (allowDiagonalMovement) {
                    addDiagonalEdges(g, p, nodes);
                }
            }
        }

        return g;
    }

    private static void addStraightEdges(Graph graph, Point fromCoords, Node[][] allNodes) {
        Node current = allNodes[fromCoords.getX()][fromCoords.getY()];
        if (current != null) {
            addEdgeToNodeIfExists(graph, current, new Point(fromCoords.getX() - 1, fromCoords.getY()), allNodes, 1);
            addEdgeToNodeIfExists(graph, current, new Point(fromCoords.getX() + 1, fromCoords.getY()), allNodes, 1);
            addEdgeToNodeIfExists(graph, current, new Point(fromCoords.getX(), fromCoords.getY() - 1), allNodes, 1);
            addEdgeToNodeIfExists(graph, current, new Point(fromCoords.getX(), fromCoords.getY() + 1), allNodes, 1);
        }
    }

    private static void addDiagonalEdges(Graph graph, Point fromCoords, Node[][] allNodes) {
        Node current = allNodes[fromCoords.getX()][fromCoords.getY()];
        if (current != null) {
            addEdgeToNodeIfExists(graph, current, new Point(fromCoords.getX() - 1, fromCoords.getY() - 1), allNodes, Math.sqrt(2));
            addEdgeToNodeIfExists(graph, current, new Point(fromCoords.getX() - 1, fromCoords.getY() + 1), allNodes, Math.sqrt(2));
            addEdgeToNodeIfExists(graph, current, new Point(fromCoords.getX() + 1, fromCoords.getY() - 1), allNodes, Math.sqrt(2));
            addEdgeToNodeIfExists(graph, current, new Point(fromCoords.getX() + 1, fromCoords.getY() + 1), allNodes, Math.sqrt(2));
        }
    }

    private static void addEdgeToNodeIfExists(Graph graph, Node from, Point toCoords, Node[][] allNodes, double cost) {
        if (toCoords.getX() >= 0 && toCoords.getX() < allNodes.length
                && toCoords.getY() >= 0 && toCoords.getY() < allNodes[0].length) {

            Node to = allNodes[toCoords.getX()][toCoords.getY()];
            if (to != null) {
                graph.addEdge(from, to, cost);
            }
        }
    }

}
