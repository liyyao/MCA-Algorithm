package basic.graph;

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
public class T06_TopologicalOrderDFS2 {

    public static class DirectedGraphNode {
        public int label;
        public ArrayList<DirectedGraphNode> neighbors;

        public DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    public static class Record {
        public DirectedGraphNode node;
        public long nodes;

        public Record(DirectedGraphNode n, long o) {
            node = n;
            nodes = o;
        }
    }

    public static class MyComparator implements Comparator<Record> {

        @Override
        public int compare(Record o1, Record o2) {
            return o1.nodes == o2.nodes ? 0 : (o1.nodes > o2.nodes ? -1 : 1);
        }
    }

    public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        Map<DirectedGraphNode, Record> order = new HashMap<>();
        for (DirectedGraphNode cur : graph) {
            f(cur, order);
        }
        List<Record> recordArr = new ArrayList<>();
        for (Record r : order.values()) {
            recordArr.add(r);
        }
        recordArr.sort(new MyComparator());
        ArrayList<DirectedGraphNode> ans = new ArrayList<DirectedGraphNode>();
        for (Record r : recordArr) {
            ans.add(r.node);
        }
        return ans;
    }

    public static Record f(DirectedGraphNode cur, Map<DirectedGraphNode, Record> order) {
        if (order.containsKey(cur)) {
            return order.get(cur);
        }
        long nodes = 0;
        for (DirectedGraphNode next : cur.neighbors) {
            nodes += f(next, order).nodes;
        }
        Record ans = new Record(cur, nodes + 1);
        order.put(cur, ans);
        return ans;
    }

}
