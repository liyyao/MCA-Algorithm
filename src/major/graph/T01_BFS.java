package major.graph;

import common.graph.GraphNode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 从Node出发，宽度（广度）优先遍历
 */
public class T01_BFS {

    public static void bfs(GraphNode start) {
        if (start == null) {
            return;
        }
        Queue<GraphNode> queue = new LinkedList<>();
        Set<GraphNode> set = new HashSet<>();
        queue.add(start);
        set.add(start);
        while (!queue.isEmpty()) {
            GraphNode cur = queue.poll();
            System.out.println(cur.value);
            for (GraphNode next : cur.nexts) {
                if (!set.contains(next)) {
                    set.add(next);
                    queue.add(next);
                }
            }
        }
    }
}
