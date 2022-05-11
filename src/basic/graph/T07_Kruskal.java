package basic.graph;

import common.graph.Graph;
import common.graph.GraphEdge;
import common.graph.GraphNode;

import java.util.*;

public class T07_Kruskal {

    public static class UnionFind {
        private Map<GraphNode, GraphNode> parentMap;
        private Map<GraphNode, Integer> sizeMap;

        public UnionFind() {
            parentMap = new HashMap<>();
            sizeMap = new HashMap<>();
        }

        private GraphNode findParent(GraphNode node) {
            Stack<GraphNode> path = new Stack<>();
            while (node != parentMap.get(node)) {
                path.add(node);
                node = parentMap.get(node);
            }
            while (!path.isEmpty()) {
                parentMap.put(path.pop(), node);
            }
            return node;
        }

        public boolean isSameSet(GraphNode a, GraphNode b) {
            return findParent(a) == findParent(b);
        }

        public void union(GraphNode a, GraphNode b) {
            if (a == null || b == null) {
                return;
            }
            GraphNode p1 = findParent(a);
            GraphNode p2 = findParent(b);
            if (p1 != p2) {
                int aSize = sizeMap.get(p1);
                int bSize = sizeMap.get(p2);
                if (aSize <= bSize) {
                    parentMap.put(p1, p2);
                    sizeMap.put(p2, aSize + bSize);
                    sizeMap.remove(p1);
                } else {
                    parentMap.put(p2, p1);
                    sizeMap.put(p1, aSize + bSize);
                    sizeMap.remove(p2);
                }
            }
        }

        public void makeSets(Collection<GraphNode> nodes) {
            parentMap.clear();
            sizeMap.clear();
            for (GraphNode node : nodes) {
                parentMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }
    }

    public static class EdgeComparator implements Comparator<GraphEdge> {

        @Override
        public int compare(GraphEdge o1, GraphEdge o2) {
            return o1.weight - o2.weight;
        }
    }

    public static Set<GraphEdge> kruskalMST(Graph graph) {
        UnionFind unionFind = new UnionFind();
        unionFind.makeSets(graph.nodes.values());
        PriorityQueue<GraphEdge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        for (GraphEdge edge : graph.edges) {    //M条边
            priorityQueue.add(edge);    //O(logM)
        }
        Set<GraphEdge> result = new HashSet<>();
        while (!priorityQueue.isEmpty()) {
            GraphEdge e = priorityQueue.poll();
            if (!unionFind.isSameSet(e.from, e.to)) {
                result.add(e);
                unionFind.union(e.from, e.to);
            }
        }
        return result;
    }
}
