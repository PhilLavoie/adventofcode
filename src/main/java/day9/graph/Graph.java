package day9.graph;

import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;

public class Graph {

    private final Map<String, Node> nodes;

    public Graph() {
        this.nodes = new HashMap<>();
    }

    public void putNode(Node node) {
        checkArgument(!hasNode(node), "already added node: " + node.getName());
        nodes.put(node.getName(), node);
    }
    
    public void connectNodes(Node node, Node otherNode, int distance) {
        checkArgument(hasNode(node), "unkown node: " + node.getName());
        checkArgument(hasNode(otherNode), "unkown node: " + otherNode.getName());

        node.addArc(new Arc(node, otherNode, distance));
        otherNode.addArc(new Arc(otherNode, node, distance));
    }
    
    public boolean hasNode(String nodeName) {
        return nodes.containsKey(nodeName);
    }
    
    private boolean hasNode(Node node) {
        return hasNode(node.getName());
    }

    public Node getNode(String nodeName) {
        return nodes.get(nodeName);
    }
    
    public Iterable<Node> getNodes() {
        return nodes.values();
    }

}
