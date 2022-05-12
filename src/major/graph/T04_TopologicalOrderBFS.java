package major.graph;

import java.util.*;

/**
 * 拓扑排序
 * OJ: https://www.lintcode.com/problem/topological-sorting
 *
 * 给定一个有向图，图节点的拓扑排序定义如下:
 *
 * 对于图中的每一条有向边 A -> B , 在拓扑排序中A一定在B之前.
 * 拓扑排序中的第一个节点可以是图中的任何一个没有其他节点指向它的节点.
 * 针对给定的有向图找到任意一种拓扑排序的顺序.
 *
 * 样例 1：
 * 输入： graph = {0,1,2,3#1,4#2,4,5#3,4,5#4#5}
 * 输出： [0, 1, 2, 3, 4, 5]
 * 拓扑排序可以为:
 *      [0, 1, 2, 3, 4, 5]
 *      [0, 2, 3, 1, 5, 4]
 *      ...
 * 您只需要返回给定图的任何一种拓扑顺序。
 *
 * 挑战
 * 能否分别用BFS和DFS完成？
 */
public class T04_TopologicalOrderBFS {

    class DirectedGraphNode {
        int label;
        List<DirectedGraphNode> neighbors;
        public DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<>();
        }
    }

    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        if (graph == null || graph.size() == 0) {
            return graph;
        }
        Map<DirectedGraphNode, Integer> inMap = new HashMap<>();
        for (DirectedGraphNode node : graph) {
            inMap.put(node, 0);
        }
        for (DirectedGraphNode node : graph) {
            for (DirectedGraphNode neighbor : node.neighbors) {
                inMap.put(neighbor, inMap.get(neighbor) + 1);
            }
        }
        Queue<DirectedGraphNode> queue = new LinkedList<>();
        for (DirectedGraphNode node : inMap.keySet()) {
            if (inMap.get(node) == 0) {
                queue.add(node);
            }
        }
        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            DirectedGraphNode cur = queue.poll();
            ans.add(cur);
            for (DirectedGraphNode node : cur.neighbors) {
                int in = inMap.get(node) - 1;
                inMap.put(node, in);
                if (in == 0) {
                    queue.offer(node);
                }
            }
        }
        return ans;
    }

}
