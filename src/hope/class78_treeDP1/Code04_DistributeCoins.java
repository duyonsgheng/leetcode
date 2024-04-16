package hope.class78_treeDP1;

import custom.base.TreeNode;

/**
 * @author Mr.Du
 * @ClassName Code04_DistributeCoins
 * @date 2024年04月07日 下午 05:26
 */
// 在二叉树中分配硬币
// 给你一个有 n 个结点的二叉树的根结点 root
// 其中树中每个结点 node 都对应有 node.val 枚硬币
// 整棵树上一共有 n 枚硬币
// 在一次移动中，我们可以选择两个相邻的结点，然后将一枚硬币从其中一个结点移动到另一个结点
// 移动可以是从父结点到子结点，或者从子结点移动到父结点
// 返回使每个结点上 只有 一枚硬币所需的 最少 移动次数
// 测试链接 : https://leetcode.cn/problems/distribute-coins-in-binary-tree/
public class Code04_DistributeCoins {

    public static int distributeCoins(TreeNode root) {
        return f(root).move;
    }

    public static Info f(TreeNode x) {
        if (x == null) {
            return new Info(0, 0, 0);
        }
        Info left = f(x.left);
        Info right = f(x.right);
        int cnt = left.cnt + right.cnt + 1;
        int sum = left.sum + right.sum + x.val;
        int move = left.move + right.move + Math.abs(left.cnt - left.sum) + Math.abs(right.cnt - right.sum);
        return new Info(cnt, sum, move);
    }

    public static class Info {
        public int cnt; // 有多少节点
        public int sum; // 有多少硬币
        public int move;// 需要给出多少

        public Info(int a, int b, int c) {
            cnt = a;
            sum = b;
            move = c;
        }
    }
}
