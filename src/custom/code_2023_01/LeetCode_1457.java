package custom.code_2023_01;

import custom.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_1457
 * @Author Duys
 * @Description
 * @Date 2023/1/17 11:19
 **/
// 1457. 二叉树中的伪回文路径
public class LeetCode_1457 {
    int ans = 0;

    public int pseudoPalindromicPaths(TreeNode root) {
        if (root == null) {
            return 1;
        }
        process(root, 0);
        return ans;
    }

    public void process(TreeNode cur, int status) {
        status ^= 1 << cur.val;
        if (cur.left == null && cur.right == null) {
            if (Integer.bitCount(status) < 2) {
                ans++;
            }
            return;
        }
        if (cur.left != null) {
            process(cur.left, status);
        }
        if (cur.right != null) {
            process(cur.right, status);
        }
    }

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(2);
        root1.left = new TreeNode(3);
        root1.left.left = new TreeNode(3);
        root1.left.right = new TreeNode(1);
        root1.right = new TreeNode(1);
        root1.right.right = new TreeNode(1);
        // System.out.println(pseudoPalindromicPaths(root1));
    }
}
