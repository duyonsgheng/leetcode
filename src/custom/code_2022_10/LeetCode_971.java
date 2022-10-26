package custom.code_2022_10;

import custom.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_971
 * @Author Duys
 * @Description
 * @Date 2022/10/25 17:37
 **/
// 971. 翻转二叉树以匹配先序遍历
public class LeetCode_971 {
    public int index;
    public List<Integer> ans;
    public int[] arr;

    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        ans = new ArrayList<>();
        index = 0;
        arr = voyage;
        dfs(root);
        if (!ans.isEmpty() && ans.get(0) == -1) {
            ans.clear();
            ans.add(-1);
        }
        return ans;
    }

    public void dfs(TreeNode cur) {
        if (cur == null) {
            return;
        }
        // 来到的节点不等
        if (cur.val != arr[index++]) {
            ans.clear();
            ans.add(-1);
            return;
        }
        // 先序遍历，如果我的左边不等于下一个，那么就需要翻转了
        if (index < arr.length && cur.left != null && cur.left.val != arr[index]) {
            ans.add(cur.val);
            // 翻转了，先先遍历右边
            dfs(cur.right);
            // 后遍历左边
            dfs(cur.left);
        } else { // 否则，当前节点不需要翻转，往下走
            dfs(cur.left);
            dfs(cur.right);
        }

    }

}
