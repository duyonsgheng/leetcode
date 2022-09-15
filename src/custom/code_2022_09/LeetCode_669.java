package custom.code_2022_09;

import custom.base.TreeNode;

/**
 * @ClassName LeetCode_669
 * @Author Duys
 * @Description
 * @Date 2022/9/6 11:12
 **/
// 669. 修剪二叉搜索树
public class LeetCode_669 {

    public static TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }
        // 二叉搜索树的性质
        if (root.val > high) {
            return trimBST(root.left, low, high);
        }
        if (root.val < low) {
            return trimBST(root.right, low, high);
        }
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(2);
        trimBST(root, 1, 2);
    }

}
