package duys_code.day_37;

/**
 * @ClassName Code_02_221_LeetCode
 * @Author Duys
 * @Description https://leetcode-cn.com/problems/maximal-square/
 * @Date 2021/12/15 13:32
 **/
public class Code_02_221_LeetCode {
    // 最大的正方形，且正方形内部及其边全部是1

    // 求解：我们求正方形的最右下角位置
    // 1.如果这个位置的左边得到的最大正方形 边长是 a
    // 2.如果这个位置的上边得到的最大正方形 边长是 b
    // 3.如果这个位置的左上得到的最大正方形 边长是 c
    // 当前位置如果是1，那么 a b c 三个求最小的 然后+1，就是这个位置的最大正方形
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length <= 0 || matrix[0].length <= 0) {
            return 0;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n + 1][m + 1];
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (matrix[i][0] == '1') {
                dp[i][0] = 1;
                max = 1;
            }
        }
        for (int j = 1; j < m; j++) {
            if (matrix[0][j] == '1') {
                dp[0][j] = 1;
                max = 1;
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i][j] != '1') {
                    continue;
                }
                dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i][j - 1]), dp[i - 1][j]) + 1;
                max = Math.max(dp[i][j], max);
            }
        }
        return max * max;
    }
}
