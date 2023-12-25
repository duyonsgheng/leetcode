package hope.subArray1;

/**
 * @author Mr.Du
 * @ClassName Code02_HouseRobber
 * @date 2023年12月25日 16:38
 */

// 数组中不能选相邻元素的最大累加和
// 给定一个数组，可以随意选择数字
// 但是不能选择相邻的数字，返回能得到的最大累加和
// 测试链接 : https://leetcode.cn/problems/house-robber/
public class Code02_HouseRobber {
    // 动态规划
    public static int rob1(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        if (n == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], Math.max(nums[i], dp[i - 2] + nums[i]));
        }
        return dp[n - 1];
    }

    // 空间压缩
    public static int rob2(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        if (n == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int prePre = nums[0];
        int pre = Math.max(nums[0], nums[1]);
        for (int i = 2, cur; i < n; i++) {
            cur = Math.max(pre, Math.max(nums[i], prePre + nums[i]));
            prePre = pre;
            pre = cur;
        }
        return pre;
    }
}
