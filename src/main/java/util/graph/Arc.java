package util.graph;

public class Arc {
    private final Node from;
    private final Node to;
    private final int distance;

    public Arc(Node from, Node to, int distance) {
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    public Node getFrom() {
        return from;
    }

    public Node getTo() {
        return to;
    }

    public int getDistance() {
        return distance;
    }
}
