package ictgradschool.amazeing.algorithms;

import ictgradschool.amazeing.graph.Graph;
import ictgradschool.amazeing.graph.Node;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.TreeMap;

public class GraphSearchAlgorithmFactory {

    private static final Map<String, Class<? extends IGraphSearchAlgorithm>> classes = new TreeMap<>();

    static {
        classes.put("Breadth-First Search", BreadthFirstSearch.class);
        classes.put("Dijkstra (Uniform Cost Search)", DijkstraSearch.class);
        classes.put("Greedy Best First Search", GreedyBestFirstSearch.class);
        classes.put("A* Search", AStarSearch.class);
    }

    public static String[] getAlgorithmNames() {
        return classes.keySet().toArray(new String[0]);
    }

    public static IGraphSearchAlgorithm createAlgorithm(String name, Graph graph, Node startNode, Node goalNode) {
        Class<? extends IGraphSearchAlgorithm> clazz = classes.get(name);
        try {
            Constructor<? extends IGraphSearchAlgorithm> ctor = clazz.getConstructor(graph.getClass(), startNode.getClass(), goalNode.getClass());
            return ctor.newInstance(graph, startNode, goalNode);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}