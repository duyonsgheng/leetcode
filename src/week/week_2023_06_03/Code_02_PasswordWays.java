package week.week_2023_06_03;

// 来自字节
// 密码是一串长度为n的小写字母，一则关于密码的线索纸条
// 首先将字母a到z编号为0到25编号
// 纸条上共有n个整数ai，其中a1表示密码里第一个字母的编号
// 若i>1的话就表示第i个字母和第i-1个字母编号的差值
// 例如，a2就代表密码中第1个字母和第2个字母编号的差值
// 若密码是acb，那么纸条上的数字就是[0, 2, 1]
// 返回可能的密码的个数，由于结果可能很大，
// 输出对1000000007取模的结果
// 1 <= n <= 10^5
// 0 <= ai <= 25
public class Code_02_PasswordWays {

    // 从左往右的尝试模型
    public static int ways1(int[] arr) {
        return process(arr, 1, arr[0]);
    }

    public static int process(int[] arr, int i, int pre) {
        if (pre < 0 || pre > 25) {
            return 0;
        }
        int ans = 0;
        if (i == arr.length) {
            ans = 1;
        } else {
            ans += process(arr, i + 1, pre - arr[i]);
            ans += process(arr, i + 1, pre + arr[i]);
        }
        return ans;
    }

    public static int ways2(int[] arr) {
        int n = arr.length;
        int mod = 1_000_000_007;
        int[][] dp = new int[n + 1][26];
        for (int i = 0; i < 26; i++) {
            dp[n][i] = 1;
        }
        for (int i = n - 1; i >= 1; i--) {
            for (int j = 0; j < 26; j++) {
                if (j - arr[i] >= 0) {
                    dp[i][j] = dp[i + 1][j - arr[i]];
                }
                if (j + arr[i] < 26) {
                    dp[i][j] = (dp[i][j] + dp[i + 1][j + arr[i]]) % mod;
                }
            }
        }
        return dp[1][arr[0]];
    }
}
