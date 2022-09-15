package custom.code_2022_06;

/**
 * @ClassName LeetCode_236
 * @Author Duys
 * @Description
 * @Date 2022/6/10 15:22
 **/
// 236. 二叉树的最近公共祖先
// https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/
public class LeetCode_236 {
    // 二叉树的递归套路
    // 首先可能性分析
    // 1.与当前x节点有关
    // 1.1 x就是a和b的公共祖先
    // 1.2 x的左树上发现了a或者b x的右树上发现了a或者b
    // 2.与当前x节点无关
    // 2.1 x的左树上发现了a b 和公共祖先
    // 2.2 x的右树上发现了a b 和公共祖先

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return process(root, p, q).parent;
    }

    public static Info process(TreeNode x, TreeNode p, TreeNode q) {
        if (x == null) {
            return new Info(false, false, null);
        }
        Info left = process(x.left, p, q);
        Info right = process(x.right, p, q);
        boolean curA = left.findA || right.findA || x == p;
        boolean curB = left.findB || right.findB || x == q;
        TreeNode ans = null;
        if (left.parent != null) {
            ans = left.parent;
        } else if (right.parent != null) {
            ans = right.parent;
        } else {
            if (curA && curB) {
                ans = x;
            }
        }
        return new Info(curA, curB, ans);
    }

    public static class Info {
        private boolean findA;
        private boolean findB;
        private TreeNode parent;

        public Info(boolean a, boolean b, TreeNode t) {
            findA = a;
            findB = b;
            parent = t;

        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
