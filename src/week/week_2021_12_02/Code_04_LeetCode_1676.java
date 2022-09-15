package week.week_2021_12_02;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName Code_04_LeetCode_1676
 * @Author Duys
 * @Description
 * @Date 2022/4/19 10:54
 **/
// https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree-iv/
// 二叉树的最低公共祖先问题 4
// 给定一棵二叉树的根节点 root 和 TreeNode 类对象的数组（列表） nodes，返回 nodes 中所有节点的最近公共祖先（LCA）。
// 数组（列表）中所有节点都存在于该二叉树中，且二叉树中所有节点的值都是互不相同的。
//我们扩展二叉树的最近公共祖先节点在维基百科上的定义：“对于任意合理的 i 值， n 个节点 p1 、 p2、…、 pn
//在二叉树 T 中的最近公共祖先节点是后代中包含所有节点 pi 的最深节点（我们允许一个节点是其自身的后代）”。
//一个节点 x 的后代节点是节点 x 到某一叶节点间的路径中的节点 y。
// 236
// 1644
// 1650
public class Code_04_LeetCode_1676 {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        Set<Integer> set = new HashSet<>();
        for (TreeNode node : nodes) {
            set.add(node.val);
        }
        return process(root, set, set.size()).find;
    }

    public static Info process(TreeNode x, Set<Integer> set, int all) {
        if (x == null) {
            return new Info(null, 0);
        }
        Info left = process(x.left, set, all);
        // 左树上找到了，往上返回
        if (left.find != null) {
            return left;
        }
        Info right = process(x.right, set, all);
        // 右树上找到了，往上返回
        if (right.find != null) {
            return right;
        }
        // 设置当前节点的返回
        int cur = set.contains(x.val) ? 1 : 0;
        set.remove(x.val);
        if (left.removes + right.removes + cur == all) {
            return new Info(x, all);
        } else {
            return new Info(null, left.removes + right.removes + cur);
        }
    }

    // 二叉树递归套路打一套
    public static class Info {

        // 最低公共祖先是谁，如果没有就是null
        public TreeNode find;
        // 当前这棵子树上删掉了几个节点，题意给出的市一批节点，看看依次能不能把节点全部处理掉
        public int removes;

        public Info(TreeNode f, int r) {
            find = f;
            removes = r;
        }
    }
}
