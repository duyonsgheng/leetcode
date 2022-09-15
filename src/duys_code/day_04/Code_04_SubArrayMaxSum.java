package duys_code.day_04;

/**
 * @ClassName Code_04_SubArrayMaxSum
 * @Author Duys
 * @Description
 * @Date 2021/9/22 18:23
 **/
public class Code_04_SubArrayMaxSum {
    /**
     * 返回一个数组中，选择的数字不能相邻的情况下，
     * 最大子序列累加和
     * <p>
     * 从左往右的尝试模型
     */
    public static int subArrayMax(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int N = arr.length;
        // dp[i] 表示 0~i上的满足条件的最大累加和
        /**
         * 可能性分析1：0 到i 位置上，我不用i位置的数，不用i，那么答案就是0到i-1的答案，因为要求不相邻
         * 可能性分析2：0到i位置上，我使用i位置的数，那么就是 0到i-2的值+i位置
         * 可能性分析3：0到i位置上，只要i位置的数，其他的都不要
         */
        int[] dp = new int[N];
        dp[0] = arr[0];
        // 因为不连续，所以1位置只能选择一个
        dp[1] = Math.max(arr[0], arr[1]);
        for (int i = 2; i < N; i++) {
            // 三种可能性求最大
            dp[i] = Math.max(arr[i], Math.min(dp[i - 1], dp[i - 2] + arr[i]));
        }
        return dp[N - 1];
    }
}
