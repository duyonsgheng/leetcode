package custom.code_2022_12;

import custom.base.TreeNode;

/**
 * @ClassName LeetCode_1339
 * @Author Duys
 * @Description
 * @Date 2022/12/13 9:46
 **/
// 1339. 分裂二叉树的最大乘积
public class LeetCode_1339 {
    long ans;
    long sum;
    int mod = (int) 1e9 + 7;

    public int maxProduct(TreeNode root) {
        ans = 0;
        sum = 0;
        sum(root);
        dfs(root);
        return (int) (ans % mod);
    }

    public void sum(TreeNode root) {
        if (root == null) {
            return;
        }
        sum += root.val;
        sum(root.right);
        sum(root.left);
    }

    public long dfs(TreeNode head) {
        if (head == null) {
            return 0;
        }
        int v = head.val;
        long l = dfs(head.left);
        long r = dfs(head.right);
        if (l != 0) {
            ans = Math.max(ans, (sum - l) * l);
        }
        if (r != 0) {
            ans = Math.max(ans, (sum - r) * r);
        }
        return l + r + v;
    }
}
