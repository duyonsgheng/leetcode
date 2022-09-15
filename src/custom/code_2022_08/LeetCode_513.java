package custom.code_2022_08;

import custom.base.TreeNode;

/**
 * @ClassName LeetCode_513
 * @Author Duys
 * @Description
 * @Date 2022/8/17 16:20
 **/
//513. 找树左下角的值
public class LeetCode_513 {
    int ans = Integer.MIN_VALUE;
    int max = Integer.MIN_VALUE;

    public int findBottomLeftValue(TreeNode root) {
        dfs(root,0);
        return ans;
    }

    public void dfs(TreeNode root, int level) {
        if (root == null) {
            return;
        }
        if (level > max) {
            max = level;
            ans = root.val;
        }
        dfs(root.left, level + 1);
        dfs(root.right, level + 1);
    }

    class Node {
        int level;
        int val;

        public Node(int l, int v) {
            level = l;
            val = v;
        }
    }
}
