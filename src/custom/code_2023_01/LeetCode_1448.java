package custom.code_2023_01;

import custom.base.TreeNode;

/**
 * @ClassName LeetCode_1448
 * @Author Duys
 * @Description
 * @Date 2023/1/6 9:56
 **/
// 1448. 统计二叉树中好节点的数目
public class LeetCode_1448 {

    public static int goodNodes(TreeNode root) {
        return process(root, Integer.MIN_VALUE);
    }

    public static int process(TreeNode root, int pre) {
        if (root == null) {
            return 0;
        }
        int ans = 0;
        if (root.val >= pre) {
            ans += 1;
            pre = root.val;
        }
        ans += process(root.left, pre);
        ans += process(root.right, pre);
        return ans;
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(3);
        t1.left = new TreeNode(1);
        t1.left.left = new TreeNode(3);
        t1.right = new TreeNode(4);
        t1.right.left = new TreeNode(1);
        t1.right.right = new TreeNode(5);
        System.out.println(goodNodes(t1));

        TreeNode t2 = new TreeNode(3);
        t2.left = new TreeNode(3);
        t2.left.left = new TreeNode(4);
        t2.left.right = new TreeNode(2);
        System.out.println(goodNodes(t2));
    }
}
