package custom.code_2022_11;

import custom.base.TreeNode;

/**
 * @ClassName LeetCode_1123
 * @Author Duys
 * @Description
 * @Date 2022/11/15 10:37
 **/
// 1123. 最深叶节点的最近公共祖先
public class LeetCode_1123 {
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        if (root == null) {
            return null;
        }
        int left = dept(root.left);
        int right = dept(root.right);
        if (left == right) {
            return root;
        } else if (left < right) {
            return lcaDeepestLeaves(root.right);
        } else {
            return lcaDeepestLeaves(root.left);
        }
    }

    public int dept(TreeNode cur) {
        if (cur == null) {
            return 0;
        }
        return 1 + Math.max(dept(cur.left), dept(cur.right));
    }
}
