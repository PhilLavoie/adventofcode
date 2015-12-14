package day9.route;

import day9.graph.Graph;

import java.util.Iterator;

import static com.google.common.collect.Lists.newLinkedList;

public class RouteEnumerator implements Iterable<Route> {

    private final Graph graph;

    public RouteEnumerator(Graph graph) {
        this.graph = graph;
    }

    @Override public Iterator<Route> iterator() {
        return new RouteIterator(graph);
    }


}
