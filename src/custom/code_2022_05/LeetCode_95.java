package custom.code_2022_05;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_95
 * @Author Duys
 * @Description
 * @Date 2022/5/17 17:03
 **/
// 给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。可以按 任意顺序 返回答案。
public class LeetCode_95 {

    // 二叉搜索树 是头节点必须大于左孩子的每一个节点，小于右孩子每一个节点
    public static List<TreeNode> generateTrees(int n) {
        if (n <= 0) {
            return new ArrayList<>();
        }
        return process(1, n);
    }

    public static List<TreeNode> process(int start, int end) {
        List<TreeNode> all = new ArrayList<>();
        if (start > end) {
            all.add(null);
            return all;
        }
        // 枚举根节点，满足性质的条件下
        for (int i = start; i <= end; i++) {
            // 左树
            List<TreeNode> lefts = process(start, i - 1);
            // 右树
            List<TreeNode> rights = process(i + 1, end);
            // 我左树都比我小，右树都比我大，构建以我为头的整棵树
            for (TreeNode left : lefts) {
                for (TreeNode right : rights) {
                    TreeNode cur = new TreeNode(i);
                    cur.left = left;
                    cur.right = right;
                    all.add(cur);
                }
            }
        }
        return all;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
