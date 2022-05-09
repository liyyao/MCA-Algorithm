package common.graph;

public class GraphEdge {
    public int weight;  //权重
    public GraphNode from;   //开始节点
    public GraphNode to;     //结束节点

    public GraphEdge(int weight, GraphNode from, GraphNode to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
