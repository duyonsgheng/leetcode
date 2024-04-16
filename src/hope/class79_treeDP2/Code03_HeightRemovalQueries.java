package hope.class79_treeDP2;

import custom.base.TreeNode;

/**
 * @author Mr.Du
 * @ClassName Code03_HeightRemovalQueries
 * @date 2024年04月08日 下午 05:14
 */
// 移除子树后的二叉树高度
// 给你一棵 二叉树 的根节点 root ，树中有 n 个节点
// 每个节点都可以被分配一个从 1 到 n 且互不相同的值
// 另给你一个长度为 m 的数组 queries
// 你必须在树上执行 m 个 独立 的查询，其中第 i 个查询你需要执行以下操作：
// 从树中 移除 以 queries[i] 的值作为根节点的子树
// 题目所用测试用例保证 queries[i] 不等于根节点的值
// 返回一个长度为 m 的数组 answer
// 其中 answer[i] 是执行第 i 个查询后树的高度
// 注意：
// 查询之间是独立的，所以在每个查询执行后，树会回到其初始状态
// 树的高度是从根到树中某个节点的 最长简单路径中的边数
// 测试链接 : https://leetcode.cn/problems/height-of-binary-tree-after-subtree-removal-queries/
public class Code03_HeightRemovalQueries {
    // 使用dfn序来实现，某一颗子树的删除
    public static final int MAXN = 100010;
    // 下标为节点的值
    public static int[] dfn = new int[MAXN];
    // 下标为dfn序号
    public static int[] deep = new int[MAXN];

    // 下标为dfn序号
    public static int[] size = new int[MAXN];
    public static int[] maxLeft = new int[MAXN]; // 从左往右依次最大值
    public static int[] maxRight = new int[MAXN];// 从右往左依次最大值
    public static int dfnCnt;

    public static int[] treeQueries(TreeNode root, int[] queries) {
        dfnCnt = 0;
        f(root, 0);
        for (int i = 1; i <= dfnCnt; i++) {
            maxLeft[i] = Math.max(maxLeft[i - 1], deep[i]);
        }
        maxRight[dfnCnt + 1] = 0;
        for (int i = dfnCnt; i >= 1; i--) {
            maxRight[i] = Math.max(maxRight[i + 1], deep[i]);
        }
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            int l = maxLeft[dfn[queries[i]] - 1];
            int r = maxRight[dfn[queries[i]] + size[dfn[queries[i]]]];
            ans[i] = Math.max(l, r);
        }
        return ans;
    }

    // 来到x节点，从头节点到x节点，经历了k条边
    public static void f(TreeNode x, int k) {
        int i = ++dfnCnt;
        dfn[x.val] = i;
        deep[i] = k;
        size[i] = 1;
        if (x.left != null) {
            f(x.left, k + 1);
            size[i] += size[dfn[x.left.val]];
        }
        if (x.right != null) {
            f(x.right, k + 1);
            size[i] += size[dfn[x.right.val]];
        }
    }
}
