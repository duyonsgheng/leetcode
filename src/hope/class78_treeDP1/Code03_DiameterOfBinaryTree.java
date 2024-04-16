package hope.class78_treeDP1;

import custom.base.TreeNode;

/**
 * @author Mr.Du
 * @ClassName Code03_DiameterOfBinaryTree
 * @date 2024年04月07日 下午 05:21
 */
// 二叉树的直径
// 给你一棵二叉树的根节点，返回该树的直径
// 二叉树的 直径 是指树中任意两个节点之间最长路径的长度
// 这条路径可能经过也可能不经过根节点 root
// 两节点之间路径的 长度 由它们之间边数表示
// 测试链接 : https://leetcode.cn/problems/diameter-of-binary-tree/
public class Code03_DiameterOfBinaryTree {
    public static int diameterOfBinaryTree(TreeNode root) {
        return f(root).diameter;
    }

    public static Info f(TreeNode x) {
        if (x == null) {
            return new Info(0, 0);
        }
        Info left = f(x.left);
        Info right = f(x.right);
        int diameter = Math.max(left.diameter, right.diameter);
        int height = Math.max(left.height, right.height) + 1;
        diameter = Math.max(diameter, left.height + right.height);
        return new Info(diameter, height);
    }

    public static class Info {
        public int diameter;
        public int height;

        public Info(int a, int b) {
            diameter = a;
            height = b;
        }
    }
}
