package hope.class76_rangeDP1;

import java.util.Arrays;

/**
 * @author Mr.Du
 * @ClassName Code03_MinimumScoreTriangulationOfPolygon
 * @date 2024年03月13日 17:43
 */
// 多边形三角剖分的最低得分
// 你有一个凸的 n 边形，其每个顶点都有一个整数值
// 给定一个整数数组values，其中values[i]是第i个顶点的值(顺时针顺序)
// 假设将多边形 剖分 为 n - 2 个三角形
// 对于每个三角形，该三角形的值是顶点标记的乘积
// 三角剖分的分数是进行三角剖分后所有 n - 2 个三角形的值之和
// 返回 多边形进行三角剖分后可以得到的最低分
// 测试链接 : https://leetcode.cn/problems/minimum-score-triangulation-of-polygon/
public class Code03_MinimumScoreTriangulationOfPolygon {

    // 暴力展开
    public static int minScoreTriangulation1(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        return f1(arr, 0, n - 1, dp);
    }

    public static int f1(int[] arr, int l, int r, int[][] dp) {
        if (dp[l][r] != -1) {
            return dp[l][r];
        }
        int ans = Integer.MAX_VALUE;
        if (l == r || l == r - 1) {
            ans = 0;
        } else {
            // 以m为中点的划分，左边得分，右边的得分，然后当前三角形的得分
            for (int m = l + 1; m < r; m++) {
                ans = Math.min(ans, f1(arr, l, m, dp) + f1(arr, m, r, dp) + arr[l] * arr[m] * arr[r]);
            }
        }
        dp[l][r] = ans;
        return ans;
    }

    // 严格位置依赖
    public static int minScoreTriangulation2(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][n];
        for (int l = n - 3; l >= 0; l--) {
            for (int r = l + 2; r < n; r++) {
                dp[l][r] = Integer.MAX_VALUE;
                for (int m = l + 1; m < r; m++) {
                    dp[l][r] = Math.min(dp[l][r], dp[l][m] + dp[m][r]) + arr[l] * arr[m] * arr[r];
                }
            }
        }
        return dp[0][n - 1];
    }
}
