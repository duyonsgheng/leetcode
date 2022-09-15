package custom.code_2022_06;

import custom.base.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName LeetCode_113
 * @Author Duys
 * @Description
 * @Date 2022/6/14 13:08
 **/
// 113. 路径总和 II
// 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
//叶子节点 是指没有子节点的节点。
//链接：https://leetcode.cn/problems/path-sum-ii
public class LeetCode_113 {

    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        process(root, targetSum, new ArrayList<>());
        return ans;
    }

    public void process(TreeNode cur, int rest, List<Integer> path) {
        if (cur == null) {
            return;
        }
        path.add(cur.val);
        if (cur.left == null && cur.right == null && cur.val == rest) {
            ans.add(new ArrayList<>(path));
        }
        process(cur.left, rest - cur.val, path);
        process(cur.right, rest - cur.val, path);
        path.remove(path.size() - 1);
    }
}
