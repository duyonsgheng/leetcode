package custom.code_2022_07;

import custom.base.TreeNode;

/**
 * @ClassName LeetCode_222
 * @Author Duys
 * @Description
 * @Date 2022/7/7 16:32
 **/
// 222. 完全二叉树的节点个数
// 给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
//完全二叉树 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，
//则该层包含 1~2^h个节点。
//链接：https://leetcode.cn/problems/count-complete-tree-nodes
public class LeetCode_222 {

    public static int countNodes1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return nums(root);
    }

    public static int nums(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = nums(root.left);
        int right = nums(root.right);
        return left + right + 1;
    }

    public static int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = level(root.left);
        int right = level(root.right);
        if (left == right) { // 满的
            return countNodes(root.right) + (1 << left);
        } else {
            return countNodes(root.left) + (1 << right);
        }
    }

    public static int level(TreeNode root) {
        int level = 0;
        while (root != null) {
            level++;
            root = root.left;
        }
        return level;
    }
}
