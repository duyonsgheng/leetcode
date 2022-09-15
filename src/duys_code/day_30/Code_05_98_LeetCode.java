package duys_code.day_30;

import java.util.LinkedList;

/**
 * @ClassName Code_05_98_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/validate-binary-search-tree/
 * @Date 2021/11/26 17:52
 **/
public class Code_05_98_LeetCode {
    // 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        LinkedList<Integer> list = new LinkedList<>();
        process(root, list);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) <= list.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    public static void process(TreeNode cur, LinkedList<Integer> list) {
        if (cur == null) {
            return;
        }
        process(cur.left, list);
        list.add(cur.val);
        process(cur.right, list);
    }

    // moirs 遍历

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
