package ictgradschool.amazeing.algorithms;

import ictgradschool.amazeing.graph.Graph;
import ictgradschool.amazeing.graph.Node;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.TreeMap;

public class GraphSearchAlgorithmFactory {

    private static GraphSearchAlgorithmFactory instance;

    public static GraphSearchAlgorithmFactory getInstance() {
        if (instance == null) {
            instance = new GraphSearchAlgorithmFactory();
        }
        return instance;
    }

    private final Map<String, Class<? extends IGraphSearchAlgorithm>> classes = new TreeMap<>();

    private GraphSearchAlgorithmFactory() {
        classes.put("Breadth-First Search", BreadthFirstSearch.class);
    }

    public String[] getAlgorithmNames() {
        return classes.keySet().toArray(new String[0]);
    }

    public IGraphSearchAlgorithm createAlgorithm(String name, Graph graph, Node startNode, Node goalNode) {
        Class<? extends IGraphSearchAlgorithm> clazz = classes.get(name);
        try {
            Constructor<? extends IGraphSearchAlgorithm> ctor = clazz.getConstructor(graph.getClass(), startNode.getClass(), goalNode.getClass());
            return ctor.newInstance(graph, startNode, goalNode);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
