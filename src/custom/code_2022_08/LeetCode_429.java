package custom.code_2022_08;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName LeetCode_429
 * @Author Duys
 * @Description
 * @Date 2022/8/10 10:56
 **/
// 429. N 叉树的层序遍历
public class LeetCode_429 {
    public static List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Deque<Node> queue = new LinkedList<>();
        queue.offerLast(root);
        while (!queue.isEmpty()) {
            List<Integer> res = new ArrayList<>();
            Deque<Node> queue1 = new LinkedList<>();
            while (!queue.isEmpty()) {
                Node cur = queue.pollFirst();
                res.add(cur.val);
                List<Node> nexts = cur.children;
                if (nexts != null) {
                    for (Node next : nexts) {
                        queue1.offerLast(next);
                    }
                }
            }
            ans.add(res);
            queue = queue1;
        }
        return ans;
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        List<Node> nodes1 = new ArrayList<>();
        Node node = new Node(3);
        nodes1.add(node);
        nodes1.add(new Node(2));
        nodes1.add(new Node(4));

        List<Node> nodes2 = new ArrayList<>();
        nodes2.add(new Node(5));
        nodes2.add(new Node(6));
        root.children = nodes1;
        node.children = nodes2;
        levelOrder(root);
    }

    static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    ;
}
