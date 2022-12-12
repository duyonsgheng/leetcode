package week.week_2022_12_01;

import custom.base.TreeNode;

/**
 * @ClassName Code_02_LeetCode_2458
 * @Author Duys
 * @Description
 * @Date 2022/12/8 9:58
 **/
// 2458. 移除子树后的二叉树高度
public class Code_02_LeetCode_2458 {
    // dfn序
    // dfn序的特点，同一个父节点的孩子节点，他们的dfn序是连续的
    // 那么我们对所有的树，按照先序遍历，给每一个节点编号，同时收集每一个节点为头的树所有的孩子节点数目
    // 那么在删除一个节点的时候，根据dfn序，知道了当前节点有几个孩子，
    // 然后每一个节点来一次左边的高度最大值，右边的高度最大值
    int max = (int) 1e5 + 10;
    int[] dnf = new int[max];
    int[] size = new int[max];
    int[] deep = new int[max];
    int[] lmax = new int[max];
    int[] rmax = new int[max];
    int n = 0;

    public int[] treeQueries(TreeNode root, int[] queries) {
        dfnBuild(root, 0);
        // dnf序从1开始的
        for (int i = 1; i <= n; i++) {
            lmax[i] = Math.max(lmax[i - 1], deep[i]);
        }
        for (int i = n; i >= 1; i--) {
            rmax[i] = Math.max(rmax[i + 1], deep[i]);
        }
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            // 比如当前是删除5节点，那么找到dfn序就是左边是1到4
            int left = lmax[dnf[queries[i]] - 1];
            // 右边就是5+size
            int right = rmax[dnf[queries[i]] + size[dnf[queries[i]]]];
            ans[i] = Math.max(left, right);
        }
        return ans;
    }

    // 当前的高度 h
    // 当前节点 root
    // 构建好整棵树的dfn编号，和对应大小，以及高度
    public void dfnBuild(TreeNode root, int h) {
        int cur = ++n;
        dnf[root.val] = cur;
        deep[cur] = h;
        size[cur] = 1;
        if (root.left != null) {
            dfnBuild(root.left, h + 1);
            size[cur] += size[dnf[root.left.val]];
        }
        if (root.right != null) {
            dfnBuild(root.right, h + 1);
            size[cur] += size[dnf[root.right.val]];
        }
    }


}
