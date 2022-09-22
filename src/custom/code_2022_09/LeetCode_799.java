package custom.code_2022_09;

/**
 * @ClassName LeetCode_799
 * @Author Duys
 * @Description
 * @Date 2022/9/22 13:44
 **/
public class LeetCode_799 {

    // 不管上层多少，水流从上往下，当前被子只需要1就满了。然后多余的往下
    // 当前i位置的杯子管下一行的哪些位置 i 和 i+1
    // 依次往下推
    public static double champagneTower(int poured, int query_row, int query_glass) {
        double[][] dp = new double[query_row + 2][query_row + 2];
        dp[0][0] = poured;
        // 从第0行的杯子开始。
        for (int r = 0; r <= query_row; r++) {
            // 当前行的每一杯能往下流多少，当前行的每一杯管住下面行的哪些位置
            for (int g = 0; g <= r; g++) {
                // 多余的水流，会分成两股，往下
                double v = (dp[r][g] - 1.0) / 2;
                if (v > 0) {
                    dp[r + 1][g] += v;
                    dp[r + 1][g + 1] += v;
                }
            }
        }
        return Math.min(1, dp[query_row][query_glass]);
    }
}
