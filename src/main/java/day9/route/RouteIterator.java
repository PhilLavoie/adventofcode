package day9.route;

import util.graph.Graph;

import java.util.Iterator;
import java.util.LinkedList;

class RouteIterator implements Iterator<Route> {

    private final LinkedList<RouteIteratorForStartNode> iteratorsForStartNodes;

    public RouteIterator(Graph graph) {
        iteratorsForStartNodes = new LinkedList<>();
        graph.getNodes().forEach(node -> iteratorsForStartNodes
            .add(RouteIteratorForStartNode.forStartNode(graph, node)));
    }

    @Override public boolean hasNext() {
        return !iteratorsForStartNodes.isEmpty();
    }

    @Override public Route next() {
        RouteIteratorForStartNode first = iteratorsForStartNodes.getFirst();

        Route route = first.next();
        if (!first.hasNext()) {
            iteratorsForStartNodes.removeFirst();
        }

        return route;
    }
}
