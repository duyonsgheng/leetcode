package week.week_2023_07_03;

import custom.base.TreeNode;

/**
 * @author Mr.Du
 * @ClassName Code_05_LeetCode_235
 * @date 2023年07月20日
 */
// https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-search-tree/
// 235. 二叉搜索树的最近公共祖先
public class Code_05_LeetCode_235 {

    // 二叉搜索树的性质是，父节点大于左孩子，小于右孩子
    // 如果遇到等于的，那么也就是当前节点了
    // 所以当我们遇到一个节点是大于p小于q，那么当前节点就是
    // 如果遇到节点大于了p q 的最大，往左边搜索
    // 如果遇到节点小于了p q 的最小，往右边搜索
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (root.val != p.val && root.val != q.val) {
            // 说明当前节点就是了
            if (Math.min(q.val, p.val) < root.val && root.val < Math.max(q.val, p.val)) {
                break;
            }
            root = root.val < Math.min(q.val, p.val) ? root.right : root.left;
        }
        return root;
    }

}
