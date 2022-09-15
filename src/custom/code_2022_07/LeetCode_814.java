package custom.code_2022_07;

import custom.base.TreeNode;

/**
 * @ClassName LeetCode_814
 * @Author Duys
 * @Description
 * @Date 2022/7/21 10:19
 **/
// 814. 二叉树剪枝
// 给你二叉树的根结点root，此外树的每个结点的值要么是 0 ，要么是 1 。
//返回移除了所有不包含 1 的子树的原二叉树。
//节点 node 的子树为 node 本身加上所有 node 的后代。
//链接：https://leetcode.cn/problems/binary-tree-pruning
public class LeetCode_814 {

    // 二叉树的递归套路
    public static TreeNode pruneTree(TreeNode root) {
        return process(root);
    }

    public static TreeNode process(TreeNode cur) {
        if (cur == null) {
            return null;
        }
        cur.left = process(cur.left);
        cur.right = process(cur.right);
        if (cur.left == null && cur.right == null && cur.val == 0) {
            return null;
        }
        return cur;
    }
}
