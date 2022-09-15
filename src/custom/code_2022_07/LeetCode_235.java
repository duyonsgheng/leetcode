package custom.code_2022_07;

import custom.base.TreeNode;

/**
 * @ClassName LeetCode_235
 * @Author Duys
 * @Description
 * @Date 2022/7/13 13:23
 **/
// 二叉树的最低公共祖先
public class LeetCode_235 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode ans = root;
        while (true) {
            // 去左树上找
            if (p.val < ans.val && q.val < ans.val) {
                ans = ans.left;
            }
            // 右树上去找
            else if (p.val > ans.val && q.val > ans.val) {
                ans = ans.right;
            } else {
                break;
            }
        }
        return ans;
    }
}
