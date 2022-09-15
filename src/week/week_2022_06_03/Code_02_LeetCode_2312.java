package week.week_2022_06_03;

/**
 * @ClassName Code_02_LeetCode_2312
 * @Author Duys
 * @Description
 * @Date 2022/6/23 13:08
 **/
// 2312. 卖木头块
// 两个核心点：
// 沿垂直方向按高度 完全 切割木块，或
// 沿水平方向按宽度 完全 切割木块
public class Code_02_LeetCode_2312 {

    // 那么我们枚举第一道切在那儿了
    public long sellingWood1(int m, int n, int[][] prices) {
        if (m < 0 || n < 0 || prices == null) {
            return 0;
        }
        int[][] vas = new int[m + 1][n + 1];
        for (int[] p : prices) {
            // 有重复的，就算最大的
            vas[p[0]][p[1]] = Math.max(p[2], vas[p[0]][p[1]]);
        }
        return process1(m, n, vas);
    }

    // 第一刀切那儿？
    // 然后横切，竖切都来一遍，得出最大
    public long process1(int m, int n, int[][] vas) {
        if (m == 0 || n == 0) { // 没了，没得切了
            return 0;
        }
        // 如果不切，就是原来的值
        long ans = vas[m][n];
        // 横着切
        for (int split = 1; split < m; split++) {
            ans = Math.max(ans, process1(split, n, vas) + process1(m - split, n, vas));
        }

        // 竖着切
        for (int split = 1; split < n; split++) {
            ans = Math.max(ans, process1(m, split, vas) + process1(m, n - split, vas));
        }
        return ans;
    }

    // 动态规划
    public long sellingWood(int m, int n, int[][] prices) {
        if (m < 0 || n < 0 || prices == null) {
            return 0;
        }
        long[][] dp = new long[m + 1][n + 1];
        // 不切的位置先填默认值
        for (int[] p : prices) {
            dp[p[0]][p[1]] = p[2];
        }
        // 枚举每一刀的位置
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 横切
                for (int split = 1; split <= (i >> 1); split++) {
                    dp[i][j] = Math.max(dp[i][j], dp[split][j] + dp[i - split][j]);
                }
                // 竖切
                for (int split = 1; split <= (j >> 1); split++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][split] + dp[i][j - split]);
                }
            }
        }
        return dp[m][n];
    }
}
