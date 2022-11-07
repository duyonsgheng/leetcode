package custom.code_2022_11;

import java.util.Arrays;

/**
 * @ClassName LeetCode_1039
 * @Author Duys
 * @Description
 * @Date 2022/11/7 14:28
 **/
// 1039. 多边形三角剖分的最低得分
public class LeetCode_1039 {

    public static int minScoreTriangulation(int[] A) {
        int n = A.length - 1;//(n+1)边形
        int[][] dp = new int[n + 1][n + 1];//从下标1处开始使用，1...n
        for (int r = 2; r <= n; r++) {
            for (int i = 1; i <= n - r + 1; i++) {
                int j = i + r - 1;
                dp[i][j] = dp[i][i] + dp[i + 1][j] + A[i - 1] * A[i] * A[j];
                for (int k = i + 1; k <= j - 1; k++) {
                    if (dp[i][k] + dp[k + 1][j] + A[i - 1] * A[k] * A[j] < dp[i][j])
                        dp[i][j] = dp[i][k] + dp[k + 1][j] + A[i - 1] * A[k] * A[j];
                }
            }
        }
        return dp[1][n];

    }

    public static void main(String[] args) {
        int[] arr = {3, 7, 4, 5};
        System.out.println(minScoreTriangulation(arr));
        System.out.println(Integer.MAX_VALUE);
    }
}
