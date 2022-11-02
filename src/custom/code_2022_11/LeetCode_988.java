package custom.code_2022_11;

import custom.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_988
 * @Author Duys
 * @Description
 * @Date 2022/11/1 15:37
 **/
//988. 从叶结点开始的最小字符串
public class LeetCode_988 {

    static List<Node> nodes;

    // 先收集所有的叶子节点
    public static String smallestFromLeaf(TreeNode root) {
        if (root == null) {
            return "";
        }
        char[] chars = new char[26];
        chars[0] = 'a';
        for (int i = 1; i < 26; i++) {
            chars[i] = (char) (chars[i - 1] + 1);
        }
        nodes = new ArrayList<>();
        Node fa = new Node(root.val);
        process(root, fa);
        String ans = null;
        for (Node node : nodes) {
            StringBuffer cur = new StringBuffer();
            Node node1 = node;
            while (node1 != null) {
                cur.append(chars[node1.val]);
                node1 = node1.next;
            }
            if (ans == null) {
                ans = cur.toString();
            } else {
                ans = ans.compareTo(cur.toString()) <= 0 ? ans : cur.toString();
            }
        }
        return ans;
    }

    public static void process(TreeNode head, Node fa) {
        if (head == null || (head.right == null && head.left == null)) {
            nodes.add(fa);
        } else {
            if (head.left != null) {
                Node cur = new Node(head.left.val);
                cur.next = fa;
                process(head.left, cur);
            }
            if (head.right != null) {
                Node cur = new Node(head.right.val);
                cur.next = fa;
                process(head.right, cur);
            }
        }
    }

    static class Node {
        int val;
        Node next;

        public Node(int v) {
            val = v;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(25);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(2);
        System.out.println(smallestFromLeaf(root));
    }
}
