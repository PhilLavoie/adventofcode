package day9.route;

import com.google.common.collect.FluentIterable;
import day9.graph.Arc;
import day9.graph.Graph;
import day9.graph.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static com.google.common.collect.Lists.newLinkedList;
import static com.google.common.collect.Sets.newHashSet;

class RouteIteratorForStartNode implements Iterator<Route> {

    private static Logger logger = LoggerFactory.getLogger(RouteIteratorForStartNode.class);

    private final LinkedList<Route> routes;

    public RouteIteratorForStartNode(LinkedList<Route> routes) {
        this.routes = routes;
    }

    private static LinkedList<Route> makeRoutes(Set<String> remainingNodesToVisit, Node currentNode,
        LinkedList<Arc> routeSoFar) {
        remainingNodesToVisit.remove(currentNode.getName());

        LinkedList<Route> routes = newLinkedList();
        if (remainingNodesToVisit.isEmpty()) {

            Route route = Route.from(routeSoFar);

            if (logger.isDebugEnabled()) {
                logger.debug("added route: {}", route.prettyString());
            }
            routes.add(route);
            return routes;
        }

        for (Arc arc : currentNode.getArcs()) {
            Node nextNode = arc.getTo();
            //Only attemp to go if we haven't been.
            if (!remainingNodesToVisit.contains(nextNode.getName())) {
                continue;
            }

            //Clone state.
            Set<String> remainingNodesToVisitClone = new HashSet<>(remainingNodesToVisit);
            LinkedList<Arc> routeSorFarClone = new LinkedList<>(routeSoFar);
            routeSorFarClone.add(arc);

            routes.addAll(makeRoutes(remainingNodesToVisitClone, nextNode, routeSorFarClone));
        }

        return routes;
    }

    public static RouteIteratorForStartNode forStartNode(Graph graph, Node startNode) {
        Set<String> remainingNodesToVisit =
            newHashSet(FluentIterable.from(graph.getNodes()).transform(node -> node.getName()));

        return new RouteIteratorForStartNode(
            makeRoutes(remainingNodesToVisit, startNode, new LinkedList<>()));
    }

    @Override public boolean hasNext() {
        return !this.routes.isEmpty();
    }

    @Override public Route next() {
        Route route = routes.getFirst();
        routes.removeFirst();
        return route;
    }

}
