package hope.class76_rangeDP1;

/**
 * @author Mr.Du
 * @ClassName Code02_PredictTheWinner
 * @date 2024年03月13日 16:45
 */
// 预测赢家
// 给你一个整数数组 nums 。玩家 1 和玩家 2 基于这个数组设计了一个游戏
// 玩家 1 和玩家 2 轮流进行自己的回合，玩家 1 先手
// 开始时，两个玩家的初始分值都是 0
// 每一回合，玩家从数组的任意一端取一个数字
// 取到的数字将会从数组中移除，数组长度减1
// 玩家选中的数字将会加到他的得分上
// 当数组中没有剩余数字可取时游戏结束
// 如果玩家 1 能成为赢家，返回 true
// 如果两个玩家得分相等，同样认为玩家 1 是游戏的赢家，也返回 true
// 你可以假设每个玩家的玩法都会使他的分数最大化
// 测试链接 : https://leetcode.cn/problems/predict-the-winner/
public class Code02_PredictTheWinner {
    // 暴力展开
    public static boolean predictTheWinner1(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int n = nums.length;
        int first = f1(nums, 0, n - 1);
        // 如果先手大于等于后手的话，就表示赢了
        return first >= sum - first;
    }

    // num[l...r]范围上的数字进行游戏，轮到玩家1
    // 返回玩家1最终能得到多少分
    public static int f1(int[] nums, int l, int r) {
        if (l == r) {
            // 只有一个数了
            return nums[l];
        }
        // 只剩下两个数了
        if (l == r - 1) {
            return Math.max(nums[l], nums[r]);
        }
        // 普遍位置
        // 可能性1：玩家1拿走了nums[l]
        int p1 = nums[l] + Math.min(f1(nums, l + 2, r), f1(nums, l + 1, r - 1));
        // 可能性2：玩家1拿走了nums[r]
        int p2 = nums[r] + Math.min(f1(nums, l, r - 2), f1(nums, l + 1, r - 1));
        return Math.max(p1, p2);
    }

    // 分析位置依赖
    // l变化范围是0到n-1，r变化范围也是0到n-1，那么就是一个二维dp
    public static boolean predictTheWinner2(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int n = nums.length;
        int[][] dp = new int[n][n];
        // 对角线是对应的值
        for (int i = 0; i < n; i++) {
            dp[i][i] = nums[i];
            dp[i][i + 1] = Math.max(nums[i], nums[i + 1]);
        }
        dp[n - 1][n - 1] = nums[n - 1];
        // 从下往上，从左往右填格子
        for (int l = n - 3; l >= 0; l--) {
            for (int r = l + 2; r < n; r++) {
                dp[l][r] = Math.max(nums[l] + Math.min(dp[l + 2][r], dp[l + 1][r - 1]), nums[r] + Math.min(dp[l][r - 2], dp[l + 1][r - 1]));
            }
        }
        int first = dp[0][n - 1];
        return first >= sum - first;
    }
}
