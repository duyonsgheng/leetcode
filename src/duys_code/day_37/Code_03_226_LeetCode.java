package duys_code.day_37;

/**
 * @ClassName Code_03_226_LeetCode
 * @Author Duys
 * @Description https://leetcode-cn.com/problems/invert-binary-tree/
 * @Date 2021/12/15 13:51
 **/
public class Code_03_226_LeetCode {
    // 二叉树的反转
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = root.left;
        // 用我的左树去接我右树的反转
        root.left = invertTree(root.right);
        // 用我的右树去接我左树的反转
        root.right = invertTree(left);
        return root;
    }


    public class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }
}
