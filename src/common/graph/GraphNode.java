package common.graph;

import java.util.ArrayList;
import java.util.List;

public class GraphNode {
    public int value;   //值
    public int in;      //入度
    public int out;     //出度
    public List<GraphNode> nexts;    //直接相邻节点
    public List<GraphEdge> edges;   //直接的边

    public GraphNode(int value) {
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
