package major.graph;

import common.graph.Graph;
import common.graph.GraphEdge;
import common.graph.GraphNode;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class T08_Prim {

    public static class EdgeComparator implements Comparator<GraphEdge> {
        @Override
        public int compare(GraphEdge o1, GraphEdge o2) {
            return o1.weight - o2.weight;
        }
    }

    public static Set<GraphEdge> primMST(Graph graph) {
        //解锁的边进入小根堆
        PriorityQueue<GraphEdge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        //哪些点被解锁出来了
        Set<GraphNode> nodeSet = new HashSet<>();
        Set<GraphEdge> result = new HashSet<>();    //依次挑选的边在result里
        for (GraphNode node : graph.nodes.values()) {   //随便挑了一个点，防止出现森林的情况
            if (!nodeSet.contains(node)) {
                nodeSet.add(node);
                for (GraphEdge edge : node.edges) { //由一个点解锁所有相连的边
                    priorityQueue.add(edge);
                }
                while (!priorityQueue.isEmpty()) {
                    GraphEdge edge = priorityQueue.poll();   //弹出解锁的边中，最小的边
                    GraphNode toNode = edge.to;     //可能的一个新的点
                    if (!nodeSet.contains(toNode)) {        //不含有的时候，就是新的点
                        nodeSet.add(toNode);
                        result.add(edge);
                        for (GraphEdge nextEdge : toNode.edges) {
                            priorityQueue.add(nextEdge);
                        }
                    }
                }
            }
            //break;        //如果是森林的情况且只要一个最小生成树的话 就放开注释
        }
        return result;
    }

    /**
     * 请保证graph是连通图
     * graph[i][j]表示点i到点j的距离，如果是系统最大值代表无路
     * 返回值是最小连通图的路径之和
     * @param graph
     * @return
     */
    public static int prim(int[][] graph) {
        int size = graph.length;
        int[] distances = new int[size];
        boolean[] visit = new boolean[size];
        visit[0] = true;
        for (int i = 0; i < size; i++) {
            distances[i] = graph[0][i];
        }
        int sum = 0;
        for (int i = 1; i < size; i++) {
            int minPath = Integer.MAX_VALUE;
            int minIndex = -1;
            for (int j = 0; j < size; j++) {
                if (!visit[j] && distances[j] < minPath) {
                    minPath = distances[j];
                    minIndex = j;
                }
            }
            if (minIndex == -1) {
                return sum;
            }
            visit[minIndex] = true;
            sum += minPath;
            for (int j = 0; j < size; j++) {
                if (!visit[j] && distances[j] > graph[minIndex][j]) {
                    distances[j] = graph[minIndex][j];
                }
            }
        }
        return sum;
    }
}
