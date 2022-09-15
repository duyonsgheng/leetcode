package week.week_2022_02_04;

/**
 * @ClassName Code_03_SpiltArrayToMaxSum
 * @Author Duys
 * @Description
 * @Date 2022/3/23 16:31
 **/
public class Code_03_SpiltArrayToMaxSum {
    //给你一个整数数组 arr，请你将该数组分隔为长度最多为 k 的一些（连续）子数组。分隔完成后，每个子数组的中的所有值都会变为该子数组中的最大值。
    //返回将数组分隔变换后能够得到的元素最大和。
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/partition-array-for-maximum-sum
    public static int maxSumAfterPartitioning(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        // dp 含义在 0到i位置上怎么划分最好
        int[] dp = new int[n];
        //
        dp[0] = arr[0];
        for (int i = 1; i < n; i++) {
            // 当前的i单独是一组
            dp[i] = arr[i] + dp[i - 1];
            int max = arr[i];
            // 看看当前i能不能和之前哪些数合并到一组去，所以要求j到i之间的数的个数不能大于k，
            for (int j = i - 1; j >= 0 && (i - j + 1) <= k; j--) {
                max = Math.max(max, arr[j]);
                dp[i] = Math.max(dp[i], max * (i - j + 1) + (j - 1 >= 0 ? dp[j - 1] : 0));
            }
        }
        return dp[n - 1];
    }
}
