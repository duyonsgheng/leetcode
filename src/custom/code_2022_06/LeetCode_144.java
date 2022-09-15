package custom.code_2022_06;

import custom.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_144
 * @Author Duys
 * @Description
 * @Date 2022/6/30 13:06
 **/
// 144. 二叉树的前序遍历
// 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
public class LeetCode_144 {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        process(root, ans);
        return ans;
    }

    public static void process(TreeNode cur, List<Integer> ans) {
        if (cur == null) {
            return;
        }
        ans.add(cur.val);
        process(cur.left, ans);
        process(cur.right, ans);
    }
}
