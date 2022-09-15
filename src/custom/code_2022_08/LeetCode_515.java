package custom.code_2022_08;

import custom.base.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName LeetCode_515
 * @Author Duys
 * @Description
 * @Date 2022/8/17 17:48
 **/
// 515. 在每个树行中找最大值
// 给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值。
public class LeetCode_515 {

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Deque<List<TreeNode>> rows = new LinkedList<>();
        rows.offerLast(Arrays.asList(root));
        while (!rows.isEmpty()) {
            List<TreeNode> treeNodes = rows.pollLast();
            if (treeNodes.size() <= 0) {
                continue;
            }
            List<TreeNode> next = new ArrayList<>();
            int max = Integer.MIN_VALUE;
            for (TreeNode node : treeNodes) {
                max = Math.max(max, node.val);
                if (node.left != null) next.add(node.left);
                if (node.right != null) next.add(node.right);
            }
            ans.add(max);
            if (next.size() > 0) rows.offerLast(next);
        }
        return ans;
    }
}
