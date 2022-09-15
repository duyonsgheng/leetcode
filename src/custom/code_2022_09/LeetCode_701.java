package custom.code_2022_09;

import custom.base.TreeNode;

/**
 * @ClassName LeetCode_701
 * @Author Duys
 * @Description
 * @Date 2022/9/8 17:37
 **/
public class LeetCode_701 {

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        TreeNode cur = root;
        while (cur != null) {
            // 往左边走
            if (val < cur.val) {
                if (cur.left == null) {
                    cur.left = new TreeNode(val);
                    break;
                } else {
                    cur = cur.left;
                }
            }
            // 往右走
            else {
                if (cur.right == null) {
                    cur.right = new TreeNode(val);
                    break;
                } else {
                    cur = cur.right;
                }
            }
        }
        return root;
    }
}
