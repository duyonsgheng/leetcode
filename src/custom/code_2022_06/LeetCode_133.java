package custom.code_2022_06;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName LeetCode_133
 * @Author Duys
 * @Description
 * @Date 2022/6/15 13:41
 **/
//133. 克隆图
// 给你无向 连通 图中一个节点的引用，请你返回该图的 深拷贝（克隆）。
// 图中的每个节点都包含它的值 val（int） 和其邻居的列表（list[Node]）。
public class LeetCode_133 {
    // 还可以使用队列
    public static Node cloneGraph(Node node) {
        if (node == null) {
            return node;
        }
        Map<Node, Node> nodes = new HashMap<>();
        return dfs(node, nodes);
    }

    public static Node dfs(Node cur, Map<Node, Node> nodeMap) {
        if (nodeMap.containsKey(cur)) {
            return nodeMap.get(cur);
        }
        Node newNode = new Node(cur.val, new ArrayList<>());
        nodeMap.put(cur, newNode);
        for (Node next : cur.neighbors) {
            newNode.neighbors.add(dfs(next, nodeMap));
        }
        return newNode;
    }

    public static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
