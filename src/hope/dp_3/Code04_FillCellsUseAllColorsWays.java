package hope.dp_3;

/**
 * @author Mr.Du
 * @ClassName Code04_FillCellsUseAllColorsWays
 * @date 2023年12月21日 16:46
 */
// 有效涂色问题
// 给定n、m两个参数
// 一共有n个格子，每个格子可以涂上一种颜色，颜色在m种里选
// 当涂满n个格子，并且m种颜色都使用了，叫一种有效方法
// 求一共有多少种有效的涂色方法
// 1 <= n, m <= 5000
// 结果比较大请 % 1000000007 之后返回
// 对数器验证
public class Code04_FillCellsUseAllColorsWays {
    public static int maxn = 5001;
    public static int[][] dp = new int[maxn][maxn];
    public static int mod = 1_000_000_007;

    public static int ways(int n, int m) {
        // dp[i][j] 一共m种颜色，前i个格子涂满j种颜色的方法
        for (int i = 1; i <= m; i++) {
            dp[i][1] = m;
        }
        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= m; j++) {
                // 前i-1个格子已经是j种颜色了，那么当前位置可以涂j种颜色
                dp[i][j] = (int) (((long) dp[i - 1][j] * j) % mod);
                dp[i][j] = (int) ((((long) dp[i - 1][j - 1] * (m - j + 1)) + dp[i][j]) % mod);
            }
        }
        return dp[n][m];
    }
}
