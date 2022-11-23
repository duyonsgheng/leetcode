package custom.code_2022_11;

import custom.base.TreeNode;

/**
 * @ClassName LeetCode_1145
 * @Author Duys
 * @Description
 * @Date 2022/11/17 14:37
 **/
//1145. 二叉树着色游戏
public class LeetCode_1145 {
    public int lc, rc, total;

    // 左节点个数 > 总数 - 左节点个数
    // 右节点个数 > 总数 - 右节点个数
    // 父节点个数 > 总数 - 父节点个数
    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        total = process(root, x);
        int rootC = total - lc - rc - 1;
        if (rootC > total - rootC) {
            return true;
        }
        if (lc > total - lc) {
            return true;
        }
        if (rc > total - rc) {
            return true;
        }
        return false;
    }

    public int process(TreeNode cur, int x) {
        if (cur == null) {
            return 0;
        }
        // 左边多少元素
        // 右边多少元素
        int l = process(cur.left, x);
        int r = process(cur.right, x);
        // 统计x节点的左右树节点个数
        if (cur.val == x) {
            lc = l;
            rc = r;
        }
        return l + r + 1;
    }
}
