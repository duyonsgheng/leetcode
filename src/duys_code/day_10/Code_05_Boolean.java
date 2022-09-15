package duys_code.day_10;

/**
 * @ClassName Code_05_Boolean
 * @Author Duys
 * @Description 力扣： https://leetcode-cn.com/problems/boolean-evaluation-lcci
 * @Date 2021/10/14 17:25
 **/
public class Code_05_Boolean {
    /**
     * 给定一个布尔表达式和一个期望的布尔结果 result，布尔表达式由 0 (false)、1 (true)、& (AND)、 | (OR) 和 ^ (XOR) 符号组成。
     * 实现一个函数，算出有几种可使该表达式得出 result 值的括号方法。
     */

    // 字符串的长度一定是奇数，偶数位置全部是数字，奇数位置全部是运算符，
    // 范围尝试，只能在偶数位置之前之后画括号。
    public static int countEval(String s, int result) {
        if (s == null || s.equals("")) {
            return 0;
        }
        char[] exp = s.toCharArray();
        int N = exp.length;
        // dp[i][j] - > 表示 i~j 范围上的结果
        Info[][] dp = new Info[N][N];
        Info process = process(exp, 0, N - 1, dp);
        return result == 0 ? process.f : process.t;
    }

    // 尝试每一个奇数位置，以当前的奇数位置的逻辑符号最后算的方法数
    public static Info process(char[] str, int L, int R, Info[][] dp) {
        if (dp[L][R] != null) {
            return dp[L][R];
        }
        int t = 0;
        int f = 0;
        if (L == R) {
            // 当只有一个位置的数字的时候，之前的已经算过了，这里这个数字单独成一个括号
            t = str[L] == '1' ? 1 : 0;
            f = str[L] == '0' ? 1 : 0;
        }
        // 这里如果还没到R位置，说明L~R还有字符，这里至少3个两个数字 一个运算符
        else {
            // L位置一定是数字，我们是要以运算符来划分
            for (int split = L + 1; split < R; split += 2) {
                // L是数字，那么 split - 1 也是数字
                Info lInfo = process(str, L, split - 1, dp);
                Info rInfo = process(str, split + 1, R, dp);
                int lt = lInfo.t;
                int lf = lInfo.f;
                int rt = rInfo.t;
                int rf = rInfo.f;
                switch (str[split]) {
                    case '&':
                        t += lt * rt;
                        f += lt * rf + lf * rt + lf * rf;
                        break;
                    case '|':
                        t += lt * rt + lt * rf + lf * rt;
                        f += lf * rf;
                        break;
                    case '^':
                        t += lt * rf + lf * rt;
                        f += lt * rt + lf * rf;
                        break;
                }
            }
        }
        Info ans = new Info(t, f);
        dp[L][R] = ans;
        return ans;
    }

    public static class Info {
        public int t;
        public int f;

        public Info(int tu, int fa) {
            t = tu;
            f = fa;
        }
    }
}
