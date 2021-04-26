package ictgradschool.amazeing.graph;

import java.util.Objects;

public class Node {

    private final Object data;

    public Node(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return data.equals(node.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }
}
