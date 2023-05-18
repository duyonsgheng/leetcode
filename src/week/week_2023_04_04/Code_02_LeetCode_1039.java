package week.week_2023_04_04;

import java.util.Arrays;

/**
 * @ClassName Code_02_LeetCode_1039
 * @Author Duys
 * @Description
 * @Date 2023/4/27 11:49
 **/
// https://leetcode.cn/problems/minimum-score-triangulation-of-polygon/
public class Code_02_LeetCode_1039 {
    public static int minScoreTriangulation(int[] values) {
        int[][] dp = new int[values.length][values.length];
        for (int i = 0; i < values.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        return process(values, 0, values.length - 1, dp);
    }

    // 在arr中 i...j这些位置上的点要分解成多个三角形
    // 三角形的一个端点是 arr[i] 另一个端点是arr[j]
    // 那么三角形的另外一个点就是 i+1 到 j-1位置上这些点
    // 得分就是arr[i] * arr[j] * arr[m]
    public static int process(int[] arr, int i, int j, int[][] dp) {
        if (i >= j - 1) {
            return 0;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        int ans = Integer.MAX_VALUE;
        for (int m = i + 1; m < j; m++) {
            ans = Math.min(ans, process(arr, i, m, dp) + process(arr, m, j, dp) + arr[i] * arr[j] * arr[m]);
        }
        return ans;
    }
}
