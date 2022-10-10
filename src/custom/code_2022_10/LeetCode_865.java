package custom.code_2022_10;

import custom.base.TreeNode;

/**
 * @ClassName LeetCode_865
 * @Author Duys
 * @Description
 * @Date 2022/10/9 13:12
 **/
// 865. 具有所有最深节点的最小子树
public class LeetCode_865 {
    // 二叉树的递归套路
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return process(root).node;
    }

    public Info process(TreeNode node) {
        if (node == null) {
            return new Info(null, 0);
        }
        // 左节点信息
        Info left = process(node.left);
        // 右节点信息
        Info right = process(node.right);
        // 谁大就返回谁
        if (left.dept > right.dept) {
            return new Info(left.node, left.dept + 1);
        }
        if (left.dept < right.dept) {
            return new Info(right.node, right.dept + 1);
        }
        // 相等就随便返回
        return new Info(node, left.dept + 1);
    }

    // 节点信息，深度
    public class Info {
        TreeNode node;
        int dept;

        public Info(TreeNode n, int d) {
            node = n;
            dept = d;
        }
    }
}
