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
public class T05_TopologicalOrderDFS {

    class DirectedGraphNode {
        int label;
        List<DirectedGraphNode> neighbors;
        public DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<>();
        }
    }

    public static class Record {
        public DirectedGraphNode node;
        public int deep;

        public Record(DirectedGraphNode n, int o) {
            this.node = n;
            this.deep = o;
        }
    }

    public static class MyComparator implements Comparator<Record> {
        @Override
        public int compare(Record o1, Record o2) {
            return o2.deep - o1.deep;
        }
    }

    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        Map<DirectedGraphNode, Record> map = new HashMap<>();
        for (DirectedGraphNode cur : graph) {
            f(cur, map);
        }
        List<Record> recordList = new ArrayList<>();
        for (Record r : map.values()) {
            recordList.add(r);
        }
        recordList.sort(new MyComparator());
        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        for (Record r : recordList) {
            ans.add(r.node);
        }
        return ans;
    }

    /**
     * 求节点所能走过的最多的点数
     * @param cur
     * @param order
     * @return
     */
    public static Record f(DirectedGraphNode cur, Map<DirectedGraphNode, Record> order) {
        if (order.containsKey(cur)) {
            return order.get(cur);
        }
        int follow = 0;
        for (DirectedGraphNode next : cur.neighbors) {
            follow = Math.max(follow, f(next, order).deep);
        }
        Record ans = new Record(cur, follow + 1);
        order.put(cur, ans);
        return ans;
    }
}
