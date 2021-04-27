package ictgradschool.amazeing.graph;

import java.util.Objects;

public class Edge {

    private final Node from, to;
    private final double cost;

    public Edge(Node from, Node to, double cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    public Edge(Node from, Node to) {
        this(from, to, 1);
    }

    public Node getFrom() {
        return from;
    }

    public Node getTo() {
        return to;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return cost == edge.cost &&
                from.equals(edge.from) &&
                to.equals(edge.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, cost);
    }
}
