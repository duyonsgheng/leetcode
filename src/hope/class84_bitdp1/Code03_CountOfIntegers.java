package hope.class84_bitdp1;

/**
 * @author Mr.Du
 * @ClassName Code03_CountOfIntegers
 * @date 2024年05月15日 下午 05:48
 */
// 统计整数数目
// 给你两个数字字符串 num1 和 num2 ，以及两个整数max_sum和min_sum
// 如果一个整数 x 满足以下条件，我们称它是一个好整数
// num1 <= x <= num2
// min_sum <= digit_sum(x) <= max_sum
// 请你返回好整数的数目
// 答案可能很大请返回答案对10^9 + 7 取余后的结果
// 注意，digit_sum(x)表示x各位数字之和
// 测试链接 : https://leetcode.cn/problems/count-of-integers/
public class Code03_CountOfIntegers {
    public static int MOD = 1000000007;

    public static int MAXN = 23;

    public static int MAXM = 401;

    public static int[][][] dp = new int[MAXN][MAXM][2];

    public static void build() {
        for (int i = 0; i < len; i++) {
            for (int j = 0; j <= max; j++) {
                dp[i][j][0] = -1;
                dp[i][j][1] = -1;
            }
        }
    }

    public static char[] num;

    public static int min, max, len;

    public static int count(String num1, String num2, int min_sum, int max_sum) {
        // 一个一个的算
        // 先算 0... Num2 所有满足的
        // 再算 0...num1 所有满足的
        // 最后检查num1 是否满足
        // 然后 大范围的-小范围的
        min = min_sum;
        max = max_sum;
        num = num2.toCharArray();
        len = num2.length();
        build();
        int ans = f(0, 0, 0);

        num = num1.toCharArray();
        len = num1.length();
        build();
        ans = (ans - f(0, 0, 0) + MOD) % MOD;
        if (check()) {
            ans = (ans + 1) % MOD;
        }
        return ans;
    }

    // 当前来到i位置
    // 之前做的决定已经小于了 num，free=1，表示后面可以自由抉择，反之为free=0
    // 之前这些决定的数字之和为sum
    public static int f(int i, int sum, int free) {
        if (sum > max) {
            return 0;
        }
        // 剩下的位置全部选9.都不行，表示不用试了
        if (sum + (len - i) * 9 < min) {
            return 0;
        }
        if (i == len) {
            return 1;
        }
        if (dp[i][sum][free] != -1) {
            return dp[i][sum][free];
        }
        int cur = num[i] - '0';
        int ans = 0;
        if (free == 0) {
            for (int p = 0; p < cur; p++) {
                ans = (ans + f(i + 1, sum + p, 1)) % MOD;
            }
            ans = (ans + f(i + 1, sum + cur, 0)) % MOD;
        } else {
            for (int p = 0; p <= 9; p++) {
                ans = (ans + f(i + 1, sum + p, 1)) % MOD;
            }
        }
        dp[i][sum][free] = ans;
        return ans;
    }

    public static boolean check() {
        int sum = 0;
        for (char cha : num) {
            sum += cha - '0';
        }
        return sum >= min && sum <= max;
    }
}
