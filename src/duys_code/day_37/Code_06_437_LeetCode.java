package duys_code.day_37;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Code_06_437_LeetCode
 * @Author Duys
 * @Description https://leetcode-cn.com/problems/path-sum-iii/
 * @Date 2021/12/16 13:18
 **/
public class Code_06_437_LeetCode {
    // 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。

    public static int pathSum(TreeNode root, int sum) {
        // map 是记录当前走过的位置的前缀和是多少，记录有多少个
        Map<Integer, Integer> preSumMap = new HashMap<>();
        preSumMap.put(0, 1);
        return process(root, sum, 0, preSumMap);
    }

    public static int process(TreeNode x, int sum, int preAll, Map<Integer, Integer> map) {
        // 来到空节点
        if (x == null) {
            return 0;
        }
        int curAll = preAll + x.val;
        int ans = 0;
        // 是否之前出现过两个差值的前缀和
        if (map.containsKey(curAll - sum)) {
            ans = map.get(curAll - sum);
        }
        if (!map.containsKey(curAll)) {
            map.put(curAll, 1);
        } else {
            map.put(curAll, map.get(curAll) + 1);
        }
        ans += process(x.left, sum, curAll, map);
        ans += process(x.right, sum, curAll, map);
        // 当前点算过了，回到当前位置的时候，只能算一次了
        if (map.get(curAll) == 1) {
            map.remove(curAll);
        } else {
            map.put(curAll, map.get(curAll) - 1);
        }
        return ans;
    }

    public class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }
}
