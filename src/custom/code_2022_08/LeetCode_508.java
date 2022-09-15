package custom.code_2022_08;

import custom.base.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName LeetCode_508
 * @Author Duys
 * @Description
 * @Date 2022/8/17 16:04
 **/
// 508. 出现次数最多的子树元素和
public class LeetCode_508 {
    Map<Integer, Integer> count = new HashMap<>();
    int max = Integer.MIN_VALUE;

    public int[] findFrequentTreeSum(TreeNode root) {
        dfs(root);
        List<Integer> list = new ArrayList<>();
        for (Integer key : count.keySet()) {
            if (count.get(key) == max) {
                list.add(key);
            }
        }
        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    public int dfs(TreeNode cur) {
        if (cur == null) {
            return 0;
        }
        int curMax = cur.val + dfs(cur.left) + dfs(cur.right);
        count.put(curMax, count.getOrDefault(curMax, 0) + 1);
        max = Math.max(max, count.get(curMax));
        return curMax;
    }
}
