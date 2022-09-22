package custom.code_2022_09;

/**
 * @ClassName LeetCode_808
 * @Author Duys
 * @Description
 * @Date 2022/9/22 16:36
 **/
// 808. 分汤
public class LeetCode_808 {

    public double soupServings(int n) {
        if (n >= 5551) {
            return 1.0;
        }
        // 向上取整
        n = (int) Math.ceil(n / 25.0);
        Double[][] dp = new Double[n + 1][n + 1];
        return process(n, n, dp);
    }

    public double process(int a, int b, Double[][] dp) {
        if (a <= 0) {
            if (b <= 0) {
                return 0.5;
            }
            return 1.0;
        }
        if (b <= 0) {
            return 0;
        }
        if (dp[a][b] != null) {
            return dp[a][b];
        }
        double ans = 0.25 * (process(a - 4, b, dp)
                + process(a - 3, b - 1, dp)
                + process(a - 2, b - 2, dp)
                + process(a - 1, b - 3, dp));
        dp[a][b] = ans;
        return ans;
    }

}
