package duys.class_07_01;

import java.util.ArrayList;

/**
 * @ClassName Node
 * @Author Duys
 * @Description 点的描述
 * @Date 2021/7/2 9:38
 **/
public class Node {
    public int value; // 自身的值
    public int in;    // 有多少点指向自己
    public int out;   // 有多少边是自己指出去的

    public ArrayList<Node> nexts; // 从自己出所有点
    public ArrayList<Edge> edges; // 从自己出去所有的边

    public Node(int v) {
        value = v;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
