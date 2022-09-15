package duys.class_07_01;

/**
 * @ClassName GraphGenerator
 * @Author Duys
 * @Description 把不熟悉的结构转换成自己的图
 * @Date 2021/7/2 9:54
 **/
public class GraphGenerator {

    // 转成我们需要的图，或者熟悉的图
    // [3,5,6] 表示 5这个点 到 6这个点 之间的距离或者权重是3
    public static Graph creatGraph(int[][] matrix) {
        Graph graph = new Graph();
        for (int i = 0; i < matrix.length; i++) {
            // 依次获取权重，from ，to
            int weight = matrix[i][0];
            int from = matrix[i][1];
            int to = matrix[i][2];
            // 如果图的点集合中不包含就需要添加，否则不处理
            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from));
            }
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to));
            }
            // 获取 from 和 to 两个点
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            // 新建一条边
            Edge newEdge = new Edge(weight, fromNode, toNode);

            // from点的下一个点是to
            fromNode.nexts.add(toNode);
            // 从from出去的出度+1
            fromNode.out++;
            // 从from出发的边
            fromNode.edges.add(newEdge);

            // 从其他点到to这个点的入度+1
            toNode.in++;

            // 图中增加一条边
            graph.edges.add(newEdge);
        }
        return graph;
    }
}
