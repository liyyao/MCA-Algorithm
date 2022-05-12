package major.graph;


import common.graph.GraphEdge;
import common.graph.GraphNode;

import java.util.HashMap;
import java.util.Map;

public class T10_Dijkstra2 {

    static class NodeRecord {
        public GraphNode node;
        public int distance;

        public NodeRecord(GraphNode node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    static class NodeHeap {
        private final GraphNode[] nodes;  //实际的堆结构
        private final Map<GraphNode, Integer> heapIndexMap;   //节点在堆上的位置
        private final Map<GraphNode, Integer> distanceMap;    //从源节点出发到该节点的目前最小距离
        private int size;      //堆上有多少个点

        public NodeHeap(int size) {
            nodes = new GraphNode[size];
            heapIndexMap = new HashMap<>();
            distanceMap = new HashMap<>();
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public NodeRecord pop() {
            NodeRecord nodeRecord = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
            swap(0, size - 1);
            heapIndexMap.put(nodes[size-1], -1);
            distanceMap.remove(nodes[size - 1]);
            nodes[size - 1] = null;
            heapify(0);
            return nodeRecord;
        }

        public void addOrUpdateOrIgnore(GraphNode node, int distance) {
            if (inHeap(node)) {
                distanceMap.put(node, Math.min(distanceMap.get(node), distance));
                insertHeapify(node, heapIndexMap.get(node));
            }
            if (!isEntered(node)) {
                nodes[size] = node;
                heapIndexMap.put(node, size);
                distanceMap.put(node, distance);
                insertHeapify(node, size++);
            }
        }

        private boolean isEntered(GraphNode node) {
            return heapIndexMap.containsKey(node);
        }

        private boolean inHeap(GraphNode node) {
            return isEntered(node) && heapIndexMap.get(node) != -1;
        }

        private void insertHeapify(GraphNode node, int index) {
            while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index - 1 ) / 2])) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private void heapify(int index) {
            int left = index * 2 + 1;
            while (left < size) {
                int smallest = left + 1 < size && distanceMap.get(nodes[left + 1]) < distanceMap.get(nodes[left]) ? left + 1 : left;
                smallest = distanceMap.get(nodes[smallest]) < distanceMap.get(nodes[index]) ? smallest : index;
                if (smallest == index) {
                    break;
                }
                swap(smallest, index);
                index = smallest;
                left = index * 2 + 1;
            }
        }

        private void swap(int index1, int index2) {
            heapIndexMap.put(nodes[index1], index2);
            heapIndexMap.put(nodes[index2], index1);
            GraphNode tmp = nodes[index1];
            nodes[index1] = nodes[index2];
            nodes[index2] = tmp;
        }
    }

    public static Map<GraphNode, Integer> dijkstra2(GraphNode head, int size) {
        NodeHeap nodeHeap = new NodeHeap(size);
        nodeHeap.addOrUpdateOrIgnore(head, 0);
        Map<GraphNode, Integer> result = new HashMap<>();
        while (!nodeHeap.isEmpty()) {
            NodeRecord record = nodeHeap.pop();
            GraphNode cur = record.node;
            int distance = record.distance;
            for (GraphEdge edge : cur.edges) {
                nodeHeap.addOrUpdateOrIgnore(edge.to, edge.weight + distance);
            }
            result.put(cur, distance);
        }
        return result;
    }
}
