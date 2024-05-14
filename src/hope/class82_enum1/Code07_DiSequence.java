package hope.class82_enum1;

/**
 * @author Mr.Du
 * @ClassName Code07_DiSequence
 * @date 2024年05月09日 下午 04:57
 */
// DI序列的有效排列
// 给定一个长度为n的字符串s，其中s[i]是:
// "D"意味着减少，"I"意味着增加
// 有效排列是对有n+1个在[0,n]范围内的整数的一个排列perm，使得对所有的i：
// 如果 s[i] == 'D'，那么 perm[i] > perm[i+1]
// 如果 s[i] == 'I'，那么 perm[i] < perm[i+1]
// 返回有效排列的perm的数量
// 因为答案可能很大，所以请返回你的答案对10^9+7取余
// 测试链接 : https://leetcode.cn/problems/valid-permutations-for-di-sequence/
public class Code07_DiSequence {
    public static int numPermsDISequence1(String s) {
        return f(s.toCharArray(), 0, s.length() + 1, s.length() + 1);
    }

    // 移动n个数字，位置范围0.....n-1
    // 当前来到i位置，i-1位置已经确定，i位置还未确定
    // i-1位置和i位置的关系在s[i-1]. D、I
    // 0到i-1位置上已经使用了 i个数字
    // 还没有使用的数字，比i-1位置小的还有less个
    // 还没有使用的数字，比i-1位置大的还有  n-i-less个
    public static int f(char[] s, int i, int less, int n) {
        int ans = 0;
        if (i == n) {
            return 1;
        }
        // 0位置的时候我们认为之前就是下降的
        if (i == 0 || s[i] == 'D') {
            // 下降的，可以从所有小的数中选，就枚举从小的数 0开始
            for (int nextLess = 0; nextLess < less; nextLess++) {
                ans += f(s, i + 1, nextLess, n);
            }
        } else {
            // 上升的，从less开始往上走，
            for (int nextLess = less, k = 1; k < n - i - less; k++, nextLess++) {
                ans += f(s, i + 1, nextLess, n);
            }
        }
        return ans;
    }

    // less 从0开始到n-i-less
    public static int numPermsDISequence2(String str) {
        int mod = 1_000_000_007;
        char[] s = str.toCharArray();
        int n = s.length + 1;
        int[][] dp = new int[n + 1][n + 1];
        // i=n的时候为1
        for (int less = 0; less <= n; less++) {
            dp[n][less] = 1;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int less = 0; less <= n; less++) {
                if (i == 0 || s[i] == 'D') {
                    // 下降的，可以从所有小的数中选，就枚举从小的数 0开始
                    for (int nextLess = 0; nextLess < less; nextLess++) {
                        dp[i][less] = (dp[i][less] + dp[i + 1][nextLess]) % mod;
                    }
                } else {
                    // 上升的，从less开始往上走，
                    for (int nextLess = less, k = 1; k < n - i - less; k++, nextLess++) {
                        dp[i][less] = (dp[i][less] + dp[i + 1][nextLess]) % mod;
                    }
                }
            }
        }
        return dp[0][n];
    }
}
