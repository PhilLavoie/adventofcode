package day9;

import util.graph.Graph;
import day9.route.Route;
import day9.route.RouteEnumerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;

public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Graph graph;
        try {
            graph = GraphParser.parse(args[0]);
        } catch (FileNotFoundException e) {
            System.out.println("could not find file: " + args[0]);
            return;
        }

        int minDistance = Integer.MAX_VALUE;
        Route routeWithMinDistance = null;

        int maxDistance = Integer.MIN_VALUE;
        Route routeWithMaxDistance = null;

        for (Route route: new RouteEnumerator(graph)) {
            if (logger.isDebugEnabled()) {
                logger.debug(route.prettyString());
            }

            int routeLength = route.getLength();
            if (routeLength < minDistance) {
                minDistance = routeLength;
                routeWithMinDistance = route;
            }

            if (routeLength > maxDistance) {
                maxDistance = routeLength;
                routeWithMaxDistance = route;
            }
        }

        System.out.println("Route with minimum distance is:");
        System.out.println("    " + routeWithMinDistance.prettyString());
        System.out.println("     total distance is: " + minDistance);
        System.out.println("Route with longest distance is: ");
        System.out.println("    " + routeWithMaxDistance.prettyString());
        System.out.println("     total distance is: " + maxDistance);

    }
}
