package duys.class_07_01;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @ClassName Graph
 * @Author Duys
 * @Description 图的描述
 * @Date 2021/7/2 9:50
 **/
public class Graph {

    public HashMap<Integer, Node> nodes; // 点
    public HashSet<Edge> edges;  // 边

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
