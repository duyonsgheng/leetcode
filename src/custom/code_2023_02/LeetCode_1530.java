package custom.code_2023_02;

import custom.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_1530
 * @Author Duys
 * @Description
 * @Date 2023/2/6 14:52
 **/
// 1530. 好叶子节点对的数量
public class LeetCode_1530 {
    //  公共祖先
    public static int countPairs(TreeNode root, int distance) {
        Node node = process(root, distance);
        return node.count;
    }

    // 节点到他的叶子节点的路径
    public static Node process(TreeNode node, int distance) {
        int[] depths = new int[distance + 1];
        // 是否是一个叶子节点
        boolean is = node.left == null && node.right == null;
        if (is) {
            depths[0] = 1;
            return new Node(depths, 0);
        }
        int[] lefts = new int[distance + 1];
        int[] rights = new int[distance + 1];
        int lcount = 0, rcount = 0;

        if (node.left != null) {
            Node left = process(node.left, distance);
            lefts = left.depths;
            lcount = left.count;
        }
        if (node.right != null) {
            Node right = process(node.right, distance);
            rights = right.depths;
            rcount = right.count;
        }
        for (int i = 0; i < distance; i++) {
            depths[i + 1] += lefts[i];
            depths[i + 1] += rights[i];
        }
        int cnt = 0;
        // 遍历距离
        for (int i = 0; i <= distance; i++) {
            for (int j = 0; j + i + 2 <= distance; j++) {
                cnt += lefts[i] * rights[j];
            }
        }
        return new Node(depths, cnt + lcount + rcount);
    }

    static class Node {
        int[] depths;
        int count;

        public Node(int[] p, int c) {
            depths = p;
            count = c;
        }
    }

    public static void main(String[] args) {
        // [1,2,3,null,4]
        // 3
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.left.right = new TreeNode(4);
        node.right = new TreeNode(3);
        System.out.println(countPairs(node, 3));
    }
}
