package major.graph;

import common.graph.GraphEdge;
import common.graph.GraphNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class T09_Dijkstra1 {

    public static Map<GraphNode, Integer> dijkstra(GraphNode from) {
        Map<GraphNode, Integer> distanceMap = new HashMap<>();
        distanceMap.put(from, 0);
        Set<GraphNode> selectedNodeSet = new HashSet<>();       //已经走过的点
        GraphNode minNode = getMinDistanceAndUnSelectedNode(distanceMap, selectedNodeSet);
        while (minNode != null) {
            int distance = distanceMap.get(minNode);
            for (GraphEdge edge : minNode.edges) {
                GraphNode toNode = edge.to;
                if (!distanceMap.containsKey(toNode)) {
                    distanceMap.put(toNode, distance + edge.weight);
                } else {
                    distanceMap.put(toNode, Math.min(distanceMap.get(toNode), distance + edge.weight));
                }
            }
            selectedNodeSet.add(minNode);
            minNode = getMinDistanceAndUnSelectedNode(distanceMap, selectedNodeSet);
        }
        return distanceMap;
    }

    private static GraphNode getMinDistanceAndUnSelectedNode(Map<GraphNode, Integer> distanceMap, Set<GraphNode> selectedNodeSet) {
        GraphNode minNode = null;
        int minDistance = Integer.MAX_VALUE;
        for (Map.Entry<GraphNode, Integer> entry : distanceMap.entrySet()) {
            GraphNode node = entry.getKey();
            int distance = entry.getValue();
            if (!selectedNodeSet.contains(node) && distance < minDistance) {
                minNode = node;
                minDistance = distance;
            }
        }
        return minNode;
    }
}
