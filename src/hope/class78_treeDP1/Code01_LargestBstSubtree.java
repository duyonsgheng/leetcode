package hope.class78_treeDP1;

import custom.base.TreeNode;

/**
 * @author Mr.Du
 * @ClassName Code01_LargestBstSubtree
 * @date 2024年04月07日 下午 04:56
 */
// 最大BST子树
// 给定一个二叉树，找到其中最大的二叉搜索树（BST）子树，并返回该子树的大小
// 其中，最大指的是子树节点数最多的
// 二叉搜索树（BST）中的所有节点都具备以下属性：
// 左子树的值小于其父（根）节点的值
// 右子树的值大于其父（根）节点的值
// 注意：子树必须包含其所有后代
// 测试链接 : https://leetcode.cn/problems/largest-bst-subtree/
public class Code01_LargestBstSubtree {

    public static int largestBSTSubtree(TreeNode root) {
        return f(root).maxBstSize;
    }

    public static Info f(TreeNode x) {
        if (x == null) {
            // 空树
            return new Info(Long.MIN_VALUE, Long.MIN_VALUE, true, 0);
        }
        Info left = f(x.left);
        Info right = f(x.right);
        long max = Math.max(x.val, Math.max(left.max, right.max));
        long min = Math.min(x.val, Math.min(left.min, right.min));
        boolean isBst = left.isBst && right.isBst && left.max < x.val && x.val < right.min;
        int maxBstSize;
        if (isBst) {
            maxBstSize = left.maxBstSize + right.maxBstSize + 1;
        } else {
            maxBstSize = Math.max(left.maxBstSize, right.maxBstSize);
        }
        return new Info(max, min, isBst, maxBstSize);
    }

    // 需要收集的信息：最大值，最小值，是否是一个二叉搜索树，最大二叉搜索数的大小
    public static class Info {
        public long max;
        public long min;
        public boolean isBst;
        public int maxBstSize;

        public Info(long a, long b, boolean c, int d) {
            max = a;
            min = b;
            isBst = c;
            maxBstSize = d;
        }
    }
}
