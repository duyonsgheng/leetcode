package custom.code_2022_09;

/**
 * @ClassName LeetCode_740
 * @Author Duys
 * @Description
 * @Date 2022/9/16 13:01
 **/
// 关联打家劫舍问题
public class LeetCode_740 {
    public int deleteAndEarn(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return 0;
        }
        int max = 0;
        for (int num : nums) {
            max = Math.max(num, max);
        }
        int[] cnt = new int[max + 1];
        int[][] dp = new int[max + 1][2];
        for (int num : nums) {
            cnt[num]++;
        }
        for (int i = 1; i <= max; i++) {
            // 当前数i不选，那么前面的比i小的数可选可不选
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            // 当前数选择了，那么前面一个数一定不能选，当前数可以选完
            dp[i][1] = dp[i - 1][0] + cnt[i] * i;
        }
        return Math.max(dp[max][0], dp[max][1]);
    }

    public int deleteAndEarn2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int max = 0;
        for (int num : nums) {
            max = Math.max(num, max);
        }
        int[] cnt = new int[max + 1];
        int[] dp = new int[max + 1];
        for (int num : nums) {
            cnt[num]++;
        }
        // 选择当前数，就得到了 dp[i-2] + i*cnt[i];
        // 不选当前数，就得到了 dp[i-1]
        dp[1] = cnt[1]; // 可选可不选。选择就 cnt[1] * 1 +0 不选就0，取最大
        for (int i = 2; i <= max; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + cnt[i] * i);
        }
        return dp[max];
    }
}
