package duys_code.day_38;

/**
 * @ClassName Code_03_617_LeetCode
 * @Author Duys
 * @Description https://leetcode-cn.com/problems/merge-two-binary-trees/
 * @Date 2021/12/20 13:32
 **/
public class Code_03_617_LeetCode {


    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        TreeNode cur = new TreeNode(root1.val + root2.val);
        cur.left = mergeTrees(root1.left, root2.left);
        cur.right = mergeTrees(root1.right, root2.right);
        return cur;
    }


    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
