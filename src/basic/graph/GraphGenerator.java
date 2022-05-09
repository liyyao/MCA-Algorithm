package basic.graph;

import common.graph.Graph;
import common.graph.GraphEdge;
import common.graph.GraphNode;

public class GraphGenerator {

    /**
     * N * 3的矩阵
     * [weight, from节点上面的值, to节点上面的值]
     * @param matrix
     * @return
     */
    public static Graph createGraph(int[][] matrix) {
        Graph graph = new Graph();
        for (int i = 0; i < matrix.length; i++) {
            int weight = matrix[i][0];
            int from = matrix[i][1];
            int to = matrix[i][2];
            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new GraphNode(from));
            }
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new GraphNode(to));
            }
            GraphNode fromNode = graph.nodes.get(from);
            GraphNode toNode = graph.nodes.get(to);
            GraphEdge newEdge = new GraphEdge(weight, fromNode, toNode);
            fromNode.nexts.add(toNode);
            fromNode.out++;
            toNode.in++;
            fromNode.edges.add(newEdge);
            graph.edges.add(newEdge);
        }
        return graph;
    }
}
