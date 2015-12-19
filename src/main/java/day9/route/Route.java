package day9.route;

import util.graph.Arc;
import util.graph.Node;

import java.util.Iterator;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class Route {

    private final List<Arc> arcs;

    private Route(List<Arc> arcs) {
        this.arcs = arcs;
    }

    public static Route from(List<Arc> arcs) {
        checkArcs(arcs);

        return new Route(arcs);
    }

    private static void checkArcs(List<Arc> arcs) {
        checkNotNull(arcs);

        Iterator<Arc> iterator = arcs.iterator();
        checkArgument(iterator.hasNext(), "route must contain at least one arc");

        Node currentNode = iterator.next().getTo();

        while (iterator.hasNext()) {
            Arc currentArc = iterator.next();
            //Making sure the chain is linked
            Node from = currentArc.getFrom();
            checkArgument(from.equals(currentNode),
                "expected arc to start at " + currentNode.getName() + " but starting at "
                    + from.getName());
            currentNode = currentArc.getTo();
        }
    }

    public int getLength() {
        return arcs.stream().mapToInt(arc -> arc.getDistance()).sum();
    }

    public String prettyString() {
        StringBuilder builder = new StringBuilder();

        Node startNode = arcs.get(0).getFrom();
        builder.append(startNode.getName());

        for (Arc arc : arcs) {
            builder.append(" -> ").append(arc.getTo().getName());
            builder.append("(").append(arc.getDistance()).append(")");
        }

        builder.append(" = ").append(getLength());
        return builder.toString();
    }
}
