package duys_code.day_20;

/**
 * @ClassName Code_04_ChildArray
 * @Author Duys
 * @Description
 * @Date 2021/11/10 11:18
 **/
public class Code_04_ChildArray {
    /**
     * 题意：
     * 给定一个字符串str，当然可以生成很多子序列
     * 返回有多少个子序列是回文子序列，空序列不算回文
     * 比如，str = “aba”
     * 回文子序列：{a}、{a}、 {a,a}、 {b}、{a,b,a}
     * 返回5
     */

    // 暴力解答：L~R范围上有多少子序列是回文
    public static int ways1(String str) {
        if (str == null || str.length() < 1) {
            return 0;
        }
        char[] s = str.toCharArray();
        char[] path = new char[s.length];
        return process1(s, 0, path, 0);
    }

    // si 再原字符串中来到的那个位置做选择
    // pi 选择的字符串成长到那儿了
    public static int process1(char[] s, int si, char[] path, int pi) {
        if (si == s.length) {
            return isP(path, pi) ? 1 : 0;
        }
        // 当前位置要和不要
        // 当前位置不要
        int ans = process1(s, si + 1, path, pi);
        // 当前位置要
        path[pi] = s[si];
        ans += process1(s, si + 1, path, pi + 1);
        return ans;
    }

    public static boolean isP(char[] path, int pi) {
        if (pi == 0) {
            return false;
        }
        int L = 0;
        int R = pi - 1;
        while (L < R) {
            if (path[L++] != path[R--]) {
                return false;
            }
        }
        return true;
    }


    // dp的解答
    public static int ways2(String str) {
        if (str == null || str.length() < 1) {
            return 0;
        }
        char[] s = str.toCharArray();
        int n = s.length;
        // dp[i][j] 表示 i到j范围上回文子序列最多是多少个
        int[][] dp = new int[n][n];
        // dp[0][0] = 1 dp[1][1] =1
        // 第一条对角线
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        // 0到1 范围上 有几个，如果s[0] == s[1] 就有三个，否则两个
        // 第二条对角线
        for (int i = 0; i < n - 1; i++) {
            dp[i][i + 1] = s[i] == s[i + 1] ? 3 : 2;
        }
        // 普遍位置。就是我们范围上的尝试
        // L ... R范围上 以开始和结束位置讨论
        // 1.包含L不包含R -> dp[L][R-1]
        // 2.包含L包含R -> s[L]==S[R] -> dp[L][R]
        // 3.不包含L，包含R -> dp[L+1][R]
        // 4.不包含L，不包含R -> dp[L+1][R-1]
        // L > R 的区域无效
        // 需要返回的是dp[0][n-1] 那么需要我们从左往右，从下往上填格子
        // 这里从第三条开始
        for (int L = n - 3; L >= 0; L--) {
            for (int R = L + 2; R < n; R++) {
                dp[L][R] = dp[L + 1][R] + dp[L][R - 1] - dp[L + 1][R - 1];
                if (s[L] == s[R]) {
                    // 多了一个单独位置的R位置
                    dp[L][R] += dp[L + 1][R - 1] + 1;
                }
            }
        }
        return dp[0][n - 1];
    }
}
