package ictgradschool.amazeing.graph;

import java.util.*;
import java.util.stream.Collectors;

public class Graph {

    private final List<Node> nodes = new ArrayList<>();
    private final Map<Node, List<Edge>> edges = new HashMap<>();

    public List<Node> getNodes() {
        return Collections.unmodifiableList(nodes);
    }

    public Map<Node, List<Edge>> getEdges() {
        return Collections.unmodifiableMap(edges);
    }

    public List<Edge> getEdgesFrom(Node node) {
        return Collections.unmodifiableList(edges.get(node));
    }

    public List<Node> getNeighbours(Node node) {
        return edges.get(node).stream().map(Edge::getTo).collect(Collectors.toList());
    }

    public void addNode(Node node) {
        nodes.add(node);
        edges.put(node, new ArrayList<>());
    }

    public Node findNode(Object data) {
        return nodes.stream().filter(node -> node.getData().equals(data)).findFirst().orElse(null);
    }

    public void addEdge(Edge edge) {
        edges.get(edge.getFrom()).add(edge);
    }

    public void addEdge(Node from, Node to, int cost) {
        addEdge(new Edge(from, to, cost));
    }

    public void addEdge(Node from, Node to) {
        addEdge(from, to, 1);
    }
}
