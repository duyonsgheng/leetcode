package custom.code_2022_06;

import custom.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_129
 * @Author Duys
 * @Description
 * @Date 2022/6/15 12:42
 **/
// 129. 求根节点到叶节点数字之和
// 给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
//每条从根节点到叶节点的路径都代表一个数字：
//例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
//计算从根节点到叶节点生成的 所有数字之和 。
//叶节点 是指没有子节点的节点。
//链接：https://leetcode.cn/problems/sum-root-to-leaf-numbers
public class LeetCode_129 {
    List<List<String>> ans = new ArrayList<>();

    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        process(root, new ArrayList<>());
        int sum = 0;
        for (List<String> cur : ans) {
            String str = String.join("", cur);
            sum += Integer.valueOf(str);
        }
        return sum;
    }

    public int porcess(TreeNode cur, int pre) {
        if (cur == null) {
            return 0;
        }
        int sum = pre * 10 + cur.val;
        if (cur.left == null && cur.right == null) {
            return sum;
        } else {
            return porcess(cur.left, sum) + porcess(cur.right, sum);
        }
    }

    public void process(TreeNode cur, List<String> path) {
        if (cur == null) {
            return;
        }
        path.add(cur.val + "");
        if (cur.left == null && cur.right == null) {
            ans.add(new ArrayList<>(path));
        }
        process(cur.left, path);
        process(cur.right, path);
        path.remove(path.size() - 1);
    }
}
