package custom.code_2022_12;

import custom.base.TreeNode;

/**
 * @ClassName LeetCode_1325
 * @Author Duys
 * @Description
 * @Date 2022/12/12 11:37
 **/
// 1325. 删除给定值的叶子节点
public class LeetCode_1325 {
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        if (root == null) {
            return null;
        }
        root.left = removeLeafNodes(root.left, target);
        root.right = removeLeafNodes(root.right, target);
        if (root.left == null && root.right == null && root.val == target) {
            return null;
        }
        return root;
    }
}
