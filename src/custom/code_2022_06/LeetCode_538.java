package custom.code_2022_06;

/**
 * @ClassName LeetCode_538
 * @Author Duys
 * @Description
 * @Date 2022/6/13 15:17
 **/
// 538. 把二叉搜索树转换为累加树
public class LeetCode_538 {
    int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        process(root);
        return root;
    }

    public void process(TreeNode cur) {
        if (cur != null) {
            process(cur.right);
            sum += cur.val;
            cur.val = sum;
            process(cur.left);
        }
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
