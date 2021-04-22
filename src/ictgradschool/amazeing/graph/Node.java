package ictgradschool.amazeing.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Node<T> {

    private final T data;

    private final List<Edge> edges = new ArrayList<>();

    public Node(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public List<Edge> getEdges() {
        return Collections.unmodifiableList(edges);
    }

    public void addEdgeTo(Node other) {
        this.edges.add(new Edge(this, other));
    }
}
