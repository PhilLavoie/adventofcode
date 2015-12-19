package util.graph;

import java.util.LinkedList;
import java.util.List;

public class Node {

    private final String name;
    private final List<Arc> arcs;

    public Node(String name) {
        this.name = name;
        this.arcs = new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    void addArc(Arc arc) {
        this.arcs.add(arc);
    }

    public Iterable<Arc> getArcs() {
        return this.arcs;
    }

    public String toString() {
        return super.toString() + ": " + this.name;
    }
}
