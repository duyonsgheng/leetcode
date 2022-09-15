package duys.class_07_01;

/**
 * @ClassName Edge
 * @Author Duys
 * @Description 边的描述
 * @Date 2021/7/2 9:38
 **/
public class Edge {
    public int weight;// 边的权重
    public Node from; // 从哪里来
    public Node to;   // 到哪里去

    public Edge() {
    }

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
