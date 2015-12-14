package day9;

import day9.graph.Graph;
import day9.graph.Node;

import java.io.*;

public class GraphParser {

    private final Graph graph;

    GraphParser(Graph graph) {
        this.graph = graph;
    }

    public static Graph parse(String fileName) throws FileNotFoundException {
        return parse(new BufferedReader(new FileReader(fileName)));
    }

    public static Graph parse(BufferedReader reader) {
        Graph graph = new Graph();
        GraphParser parser = new GraphParser(graph);
        reader.lines().forEach(parser::parseEntry);
        return graph;
    }

    private void parseEntry(String entry) {
        //Entries are of type: "node1 to node2 = distance".
        String[] words = entry.split("\\s+");
        connectNodes(words[0], words[2], words[4]);
    }

    private void connectNodes(String startName, String stopName, String distanceString) {
        Node start = getOrCreateNode(startName);
        Node stop = getOrCreateNode(stopName);

        int distance = Integer.parseInt(distanceString);
        graph.connectNodes(start, stop, distance);
    }

    private Node getOrCreateNode(String nodeName) {
        if (graph.hasNode(nodeName)) {
            return graph.getNode(nodeName);
        }
        Node node = new Node(nodeName);
        graph.putNode(node);
        return node;
    }
}
