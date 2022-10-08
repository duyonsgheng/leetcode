package week.week_2022_09_04;

/**
 * @ClassName Code_03_LeetCode_1931
 * @Author Duys
 * @Description
 * @Date 2022/9/29 13:02
 **/
// 1931. 用三种不同颜色为网格涂色
public class Code_04_LeetCode_1931 {
    public static final int mod = 1000000007;
    // 1 <= m <= 5
    // 1 <= n <= 1000

    // 轮廓线DP
    // 有3种不同颜色，那么二进制位置种，需要两位来代表一种颜色
    public int colorTheGrid(int m, int n) {
        int status = 1 << (m << 1);// 每两位表示一种颜色
        // 三个可变参数
        int[][][] dp = new int[n][m][status];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int s = 0; s < status; s++) {
                    dp[i][j][s] = -1;
                }
            }
        }
        return process(0, 0, 0, n, m, dp);
    }

    // 当前来到i行 j列 前一行的状态时status
    public static int process(int i, int j, int status, int n, int m, int[][][] dp) {
        if (i == n) { // 找到一种有效的
            return 1;
        }
        if (j == m) { // 下一行去
            return process(i + 1, 0, status, n, m, dp);
        }
        if (dp[i][j][status] != -1) {
            return dp[i][j][status];
        }
        // 3 是 1 1 我们可以拿到当前位置的上面填的是啥
        int up = (status >> (j << 1)) & 3;
        // ((s >> ((j - 1) << 1)) & 3)
        // 0 0 0 0 0 0
        //       j
        // 把左边的状态拿出来
        int left = j == 0 ? 0 : ((status >> ((j - 1) << 1)) & 3);
        int ans = 0;
        if (up != 1 && left != 1) { // 当前位置可以填1号颜色，把当前位置复位，然后把1或进去
            ans += process(i, j + 1, (status ^ (up << (j << 1))) | (1 << (j << 1)), n, m, dp);
            ans %= mod;
        }
        if (up != 2 && left != 2) { // 当前位置可以填2号颜色，把当前位置复位，然后把1或进去
            ans += process(i, j + 1, (status ^ (up << (j << 1))) | (2 << (j << 1)), n, m, dp);
            ans %= mod;
        }
        if (up != 3 && left != 3) { // 当前位置可以填3号颜色，把当前位置复位，然后把1或进去
            ans += process(i, j + 1, (status ^ (up << (j << 1))) | (3 << (j << 1)), n, m, dp);
            ans %= mod;
        }
        dp[i][j][status] = ans;
        return ans;
    }
}
