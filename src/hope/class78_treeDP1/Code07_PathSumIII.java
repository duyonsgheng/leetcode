package hope.class78_treeDP1;

import custom.base.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mr.Du
 * @ClassName Code07_PathSumIII
 * @date 2024年04月08日 上午 10:33
 */
// 路径总和 III
// 给定一个二叉树的根节点 root ，和一个整数 targetSum
// 求该二叉树里节点值之和等于 targetSum 的 路径 的数目
// 路径 不需要从根节点开始，也不需要在叶子节点结束
// 但是路径方向必须是向下的（只能从父节点到子节点）
// 测试链接 : https://leetcode.cn/problems/path-sum-iii/
public class Code07_PathSumIII {

    public static int ans;

    public static int pathSum(TreeNode root, int sum) {
        Map<Long, Integer> preSum = new HashMap<>(); // 走过的前缀和有几个了
        preSum.put(0L, 1);
        ans = 0;
        f(root, sum, 0, preSum);
        return ans;
    }

    public static void f(TreeNode x, int target, long sum, Map<Long, Integer> preSum) {
        if (x == null) {
            return;
        }
        sum += x.val;
        // 找前缀
        ans += preSum.getOrDefault(sum - target, 0);
        preSum.put(sum, preSum.getOrDefault(sum, 0) + 1);
        f(x.left, target, sum, preSum);
        f(x.right, target, sum, preSum);
        preSum.put(sum, preSum.get(sum) - 1); // 要回到头节点了，需要清理路径
    }
}
