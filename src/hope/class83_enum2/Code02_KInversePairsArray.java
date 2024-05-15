package hope.class83_enum2;

/**
 * @author Mr.Du
 * @ClassName Code02_KInversePairsArray
 * @date 2024年05月15日 上午 09:37
 */
// K个逆序对数组
// 逆序对的定义如下：
// 对于数组nums的第i个和第j个元素
// 如果满足0<=i<j<nums.length 且 nums[i]>nums[j]，则为一个逆序对
// 给你两个整数n和k，找出所有包含从1到n的数字
// 且恰好拥有k个逆序对的不同的数组的个数
// 由于答案可能很大，只需要返回对10^9+7取余的结果
// 测试链接 : https://leetcode.cn/problems/k-inverse-pairs-array/
public class Code02_KInversePairsArray {
    static int mod = 1_000_000_007;

    // 普通的，不做枚举优化的版本
    public static int kInversePairs1(int n, int k) {
        // dp[i][j] ：1.2.3...i这些数字形成j个逆序对，有多少排列
        int[][] dp = new int[n + 1][k + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= k; j++) {
                // dp[5][3]
                // 依赖那些位置呢？
                // 如果把最大的放在末尾位置，那么就是依赖dp[4][3]
                // 如果把最大的放在倒数第二位置，已经有1个逆序对了，那么就是依赖dp[4][2]
                // 如果把最大的放在倒数第三位置，已经有2个逆序对了，那么就是依赖dp[4][1]
                // 如果把最大的放在倒数第四位置，已经有3个逆序对了，那么就是依赖dp[4][0]
                if (i > j) {
                    for (int p = 0; p <= j; p++) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][p]) % mod;
                    }
                } else {
                    // dp[5][8]
                    // 同理，dp[4][8] dp[4][7] dp[4][6] dp[4][5] dp[4][4]
                    for (int p = j - i + 1; p <= j; p++) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][p]) % mod;
                    }
                }
            }
        }
        return dp[n][k];
    }

    // 用窗口
    public static int kInversePairs2(int n, int k) {
        // dp[i][j] ：1.2.3...i这些数字形成j个逆序对，有多少排列
        int[][] dp = new int[n + 1][k + 1];
        dp[0][0] = 1;
        for (int i = 1, window; i <= n; i++) {
            dp[i][0] = 1;
            window = 1;
            for (int j = 1; j <= k; j++) {
                // dp[5][3]
                // 依赖那些位置呢？
                // 如果把最大的放在末尾位置，那么就是依赖dp[4][3]
                // 如果把最大的放在倒数第二位置，已经有1个逆序对了，那么就是依赖dp[4][2]
                // 如果把最大的放在倒数第三位置，已经有2个逆序对了，那么就是依赖dp[4][1]
                // 如果把最大的放在倒数第四位置，已经有3个逆序对了，那么就是依赖dp[4][0]
                // 窗口一直增加，
                if (i > j) {
                    window = (window + dp[i - 1][j]) % mod;
                } else { // 窗口需要维持一个平衡，左边出一个，然后右边进一个
                    // dp[5][8]
                    // 同理，dp[4][8] dp[4][7] dp[4][6] dp[4][5] dp[4][4]
                    window = ((window + dp[i - 1][j]) % mod - dp[i - 1][j - i] + mod) % mod;
                }
                dp[i][j] = window;
            }
        }
        return dp[n][k];
    }
}
