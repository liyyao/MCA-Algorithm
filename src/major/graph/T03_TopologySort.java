package major.graph;

import common.graph.Graph;
import common.graph.GraphNode;

import java.util.*;

/**
 * 拓扑排序
 */
public class T03_TopologySort {

    public static List<GraphNode> sortedTopology(Graph graph) {
        Map<GraphNode, Integer> map  = new HashMap<>();
        Queue<GraphNode> zeroInQueue = new LinkedList<>();
        for (GraphNode node : graph.nodes.values()) {
            map.put(node, node.in);
            if (node.in == 0) {
                zeroInQueue.add(node);
            }
        }
        List<GraphNode> result = new ArrayList<>();
        while (!zeroInQueue.isEmpty()) {
            GraphNode cur = zeroInQueue.poll();
            result.add(cur);
            for (GraphNode next : cur.nexts) {
                map.put(next, map.get(next) - 1);
                if (map.get(next) == 0) {
                    zeroInQueue.add(next);
                }
            }
        }
        return result;
    }
}
